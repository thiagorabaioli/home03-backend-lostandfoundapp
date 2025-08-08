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
import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.entities.Owner;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.entities.enums.TYPEOFINTERACTION;
import tfr.LostAndFoundAPP.repositories.ItemLostRepository;
import tfr.LostAndFoundAPP.repositories.OrderItemRepository;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;
import tfr.LostAndFoundAPP.services.exceptions.DatabaseException;
import tfr.LostAndFoundAPP.services.exceptions.ResourceNotFoundException;

import java.time.Instant;
import java.time.LocalDate;
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

        // Lógica para enviar e-mail após o registo
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

        // Lógica para enviar o e-mail após a entrega
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