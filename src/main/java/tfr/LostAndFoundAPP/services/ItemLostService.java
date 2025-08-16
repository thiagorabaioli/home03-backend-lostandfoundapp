package tfr.LostAndFoundAPP.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tfr.LostAndFoundAPP.DTO.entities.*;
import tfr.LostAndFoundAPP.entities.*;
import tfr.LostAndFoundAPP.entities.enums.TYPEOFINTERACTION;
import tfr.LostAndFoundAPP.repositories.CollectionCenterRepository;
import tfr.LostAndFoundAPP.repositories.ItemLostRepository;
import tfr.LostAndFoundAPP.repositories.OrderItemRepository;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;
import tfr.LostAndFoundAPP.services.exceptions.DatabaseException;
import tfr.LostAndFoundAPP.services.exceptions.ResourceNotFoundException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemLostService {

    @Autowired
    private ItemLostRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserAPPService userAppService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserAPPRepository userAPPRepository;

    @Autowired
    private CollectionCenterRepository collectionCenterRepository;

    @Transactional(readOnly = true)
    public ItemLostDTO findById(Long id){
        Optional<ItemLost> entity = repository.findById(id);
        return entity.map(ItemLostDTO::new).orElseThrow( () -> new ResourceNotFoundException("ItemLost not found with id " + id));
    }

    @Transactional(readOnly = true)
    public Page<ItemLostDTO> findAllPage(Pageable pageable){
        Page<ItemLost> entity = repository.findAll(pageable);
        return entity.map(ItemLostDTO::new);
    }

    @Transactional
    public ItemLostDTO insertItem(ItemLostDTO dto){
        ItemLost entity = new ItemLost();
        copyDtoToEntity(dto, entity);
        entity.setStatus(true);
        entity = repository.save(entity);

        UserAPP user = userAppService.authenticate();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemLost(entity);
        orderItem.setUserAPP(user);
        orderItem.setType(TYPEOFINTERACTION.INSERT);
        orderItem.setInteractionDate(Instant.now());
        orderItem.setNotes("Item created by user " + user.getName());
        orderItemRepository.save(orderItem);

        try {
            List<UserAPP> usersToNotify = userAPPRepository.findUsersByRoles(Arrays.asList("ROLE_ADMIN", "ROLE_VIGILANTE"));
            String subject = "Novo Item Perdido Registado: ID " + entity.getId();
            String body = "Um novo item perdido foi registado no sistema com a seguinte descrição: '"
                    + entity.getDescription() + "' (ID: " + entity.getId() + ").";

            for (UserAPP userToNotify : usersToNotify) {
                emailService.sendEmail(userToNotify.getEmail(), subject, body);
            }
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail de notificação de novo item: " + e.getMessage());
        }

        return new ItemLostDTO(entity);
    }

    @Transactional
    public ItemLostDTO deliver(Long id, OwnerDTO ownerDto) {
        ItemLost itemLost = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item perdido não encontrado com o id " + id));

        if (!itemLost.isStatus()) {
            throw new DatabaseException("Este item já foi entregue.");
        }

        itemLost.setStatus(false);
        Owner delivery = new Owner();
        delivery.setName(ownerDto.getName());
        delivery.setEmail(ownerDto.getEmail());
        delivery.setContact(ownerDto.getContact());
        delivery.setLocation(ownerDto.getLocation());
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setItemLost(itemLost);
        delivery.setConditionAccepted(ownerDto.isConditionAccepted());
        itemLost.setDelivery(delivery);

        UserAPP user = userAppService.authenticate();
        OrderItem orderItem = new OrderItem();
        orderItem.setItemLost(itemLost);
        orderItem.setUserAPP(user);
        orderItem.setType(TYPEOFINTERACTION.DELIVERY);
        orderItem.setInteractionDate(Instant.now());
        orderItem.setNotes("Item entregue a " + ownerDto.getName() + " pelo utilizador " + user.getName());
        orderItemRepository.save(orderItem);
        itemLost.getOrderItems().add(orderItem);

        itemLost = repository.save(itemLost);

        try {
            List<UserAPP> usersToNotify = userAPPRepository.findUsersByRoles(Arrays.asList("ROLE_ADMIN", "ROLE_VIGILANTE"));
            String subject = "Item Entregue: ID " + itemLost.getId();
            String body = "O item '" + itemLost.getDescription() + "' (ID: " + itemLost.getId() + ") "
                    + "foi entregue na data " + delivery.getDeliveryDate()
                    + " ao proprietário: " + delivery.getName() + ".";

            for (UserAPP userToNotify : usersToNotify) {
                emailService.sendEmail(userToNotify.getEmail(), subject, body);
            }
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail de notificação de entrega: " + e.getMessage());
        }

        return new ItemLostDTO(itemLost);
    }

    @Transactional
    public void deliverItemsInBatch(BatchDeliveryDTO dto) {
        UserAPP user = userAppService.authenticate();

        if (!dto.isTermsAccepted()) {
            throw new DatabaseException("Os termos de recebimento devem ser aceites.");
        }

        CollectionCenter center = new CollectionCenter();
        center.setName(dto.getCenterName());
        center.setDeliveryDate(dto.getDeliveryDate());

        center = collectionCenterRepository.save(center);

        List<ItemLost> processedItems = new ArrayList<>();

        for (Long itemId : dto.getItemIds()) {
            ItemLost item = repository.findById(itemId)
                    .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com ID: " + itemId));

            if (!item.isStatus()) {
                throw new DatabaseException("O item com ID " + itemId + " já foi entregue.");
            }

            item.setStatus(false);
            item.setCollectionCenter(center);
            item.setDelivery(center);
            repository.save(item);
            processedItems.add(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setItemLost(item);
            orderItem.setUserAPP(user);
            orderItem.setType(TYPEOFINTERACTION.DELIVERY);
            orderItem.setInteractionDate(Instant.now());
            orderItem.setNotes("Item entregue em lote ao centro '" + dto.getCenterName() + "' pelo utilizador " + user.getName());
            orderItemRepository.save(orderItem);
        }

        // --- INÍCIO DA LÓGICA DE EMAIL ADICIONADA ---
        try {
            List<UserAPP> usersToNotify = userAPPRepository.findUsersByRoles(Arrays.asList("ROLE_ADMIN", "ROLE_VIGILANTE"));
            String subject = "Entrega de Itens em Lote Realizada";

            StringBuilder bodyBuilder = new StringBuilder();
            bodyBuilder.append("Foi realizada uma entrega de ").append(processedItems.size()).append(" itens em lote:\n\n");
            bodyBuilder.append("Centro de Recolha: ").append(dto.getCenterName()).append("\n");
            bodyBuilder.append("Data da Entrega: ").append(dto.getDeliveryDate()).append("\n\n");
            bodyBuilder.append("Itens Entregues:\n");

            for (ItemLost item : processedItems) {
                bodyBuilder.append("- ID ").append(item.getId()).append(": ").append(item.getDescription()).append("\n");
            }

            String body = bodyBuilder.toString();

            for (UserAPP userToNotify : usersToNotify) {
                emailService.sendEmail(userToNotify.getEmail(), subject, body);
            }
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail de notificação de entrega em lote: " + e.getMessage());
        }
        // --- FIM DA LÓGICA DE EMAIL ---
    }

    @Transactional
    public ItemLostDTO update(ItemLostDTO dto, Long id){
        try{
            ItemLost entity = repository.getReferenceById(id);
            copyToDto(dto, entity);
            entity = repository.save(entity);
            return dto;
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ItemLost not found with id " + id);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Not Found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ItemLostDTO dto, ItemLost entity){
        entity.setStatus(dto.isStatus());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setFoundDate(dto.getFoundDate());
        entity.setWhoFind(dto.getWhoFind());
        entity.setImgUrl(dto.getImgUrl());
    }

    @Transactional(readOnly = true)
    public List<ItemLostMinDTO> findPublicItems() {
        List<ItemLost> result = repository.findByStatusTrue();
        return result.stream().map(ItemLostMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<DeliveredItemDetailsDTO> findDeliveredItems() {
        List<ItemLost> result = repository.findByStatusFalse();
        return result.stream().map(DeliveredItemDetailsDTO::new).collect(Collectors.toList());
    }

    private void copyToDto(ItemLostDTO dto, ItemLost entity){
        entity.setStatus(dto.isStatus());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setFoundDate(dto.getFoundDate());
        entity.setWhoFind(dto.getWhoFind());
        entity.setImgUrl(dto.getImgUrl());
        entity.getOrderItems().clear();

        for (OrderItemDTO itemDto : dto.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setType(itemDto.getType());
            orderItem.setNotes(itemDto.getNotes());
            orderItem.setInteractionDate(itemDto.getInteractionDate());
            orderItem.setItemLost(entity);
            entity.getOrderItems().add(orderItem);
        }
    }
}