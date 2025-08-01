package tfr.LostAndFoundAPP.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tfr.LostAndFoundAPP.DTO.entities.ItemLostDTO;
import tfr.LostAndFoundAPP.DTO.entities.OrderItemDTO;
import tfr.LostAndFoundAPP.DTO.entities.OwnerDTO;
import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.entities.Owner;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.entities.enums.TYPEOFINTERACTION;
import tfr.LostAndFoundAPP.repositories.ItemLostRepository;
import tfr.LostAndFoundAPP.repositories.OrderItemRepository;
import tfr.LostAndFoundAPP.services.exceptions.DatabaseException;
import tfr.LostAndFoundAPP.services.exceptions.ResourceNotFoundException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ItemLostService {

    @Autowired
    private ItemLostRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserAPPService userAppService;

    @Transactional(readOnly = true)
    public ItemLostDTO findById(Long id){
        Optional<ItemLost> entity = repository.findById(id);
        return entity.map(x -> new ItemLostDTO(x)).orElseThrow( () -> new ResourceNotFoundException("ItemLost not found with id " + id));

    }

    @Transactional(readOnly = true)
    public Page<ItemLostDTO> findAllPage(Pageable pageable){
        Page<ItemLost> entity = repository.findAll(pageable);
        return entity.map(x -> new ItemLostDTO(x));
    }

    @Transactional
    public ItemLostDTO insertItem(ItemLostDTO dto){
        // 1. Cria uma nova entidade ItemLost vazia
        ItemLost entity = new ItemLost();

        // 2. Copia os dados BÁSICOS (location, description, etc.) do DTO para a entidade.
        // O `copyDtoToEntity` agora só faz isto, ignora o `orderItems`.
        copyDtoToEntity(dto, entity);

        // 3. Guarda o novo ItemLost na base de dados
        entity = repository.save(entity);

        // --- LÓGICA DE CRIAÇÃO DO ORDERITEM ---
        // Esta parte acontece DEPOIS de guardar e é independente do copyDtoToEntity.

        // 4. Obtém o utilizador que fez a operação
        UserAPP user = userAppService.authenticate();

        // 5. Cria o PRIMEIRO registo de interação (o INSERT)
        OrderItem orderItem = new OrderItem();
        orderItem.setItemLost(entity);
        orderItem.setUserAPP(user);
        orderItem.setType(TYPEOFINTERACTION.INSERT); // Tipo INSERT
        orderItem.setInteractionDate(Instant.now());
        orderItem.setNotes("Item created by user " + user.getName());

        // 6. Guarda este novo OrderItem
        orderItemRepository.save(orderItem);
        // --- FIM DA LÓGICA ---

        // 7. Retorna o DTO completo, que agora já inclui o OrderItem criado
        return new ItemLostDTO(entity);
    }
    public ItemLostDTO insert(ItemLostDTO dto){
        ItemLost entity = new ItemLost();
        copyToDto(dto, entity);
        entity = repository.save(entity);
        UserAPP user = userAppService.authenticate();

        OrderItem orderItem = new OrderItem();
        orderItem.setItemLost(entity);
        orderItem.setUserAPP(user);
        orderItem.setType(TYPEOFINTERACTION.INSERT);
        orderItem.setInteractionDate(Instant.now());
        orderItem.setNotes("Item created by user " + user.getName());
        orderItemRepository.save(orderItem);

        return new ItemLostDTO(entity);

    }

    @Transactional
    public ItemLostDTO delivery(ItemLostDTO dto, Long id){
        try{
            ItemLost entity = repository.getReferenceById(id);

            // 1. Guarda o estado original antes de qualquer alteração
            boolean originalStatus = entity.isStatus();

            // 2. Copia os novos dados do DTO para a entidade
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);

            // 3. Verifica se o status mudou de 'perdido' (true) para 'entregue' (false)
            if (originalStatus && !entity.isStatus()) {
                UserAPP user = userAppService.authenticate();
                OrderItem orderItem = new OrderItem();
                orderItem.setItemLost(entity);
                orderItem.setUserAPP(user);
                orderItem.setType(TYPEOFINTERACTION.DELIVERY);
                orderItem.setInteractionDate(Instant.now());
                orderItem.setNotes("Item entregue ao dono pelo utilizador " + user.getName());

                // Guarda a nova interação e adiciona-a à lista na entidade
                orderItemRepository.save(orderItem);
                entity.getOrderItems().add(orderItem);
            }

            return new ItemLostDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ItemLost not found with id " + id);
        }

    }

    @Transactional
    public ItemLostDTO deliver(Long id, OwnerDTO ownerDto) {
        try {
            ItemLost itemLost = repository.getReferenceById(id);

            if (!itemLost.isStatus()) {
                throw new DatabaseException("Este item já foi entregue.");
            }

            // 1. Mudar o status do ItemLost para 'entregue'
            itemLost.setStatus(false);

            // 2. Criar e associar a entidade Delivery (Owner)
            Owner delivery = new Owner();
            delivery.setName(ownerDto.getName());
            delivery.setEmail(ownerDto.getEmail());
            delivery.setContact(ownerDto.getContact());
            delivery.setLocation(ownerDto.getLocation());
            delivery.setDeliveryDate(LocalDate.now());
            delivery.setItemLost(itemLost);
            itemLost.setDelivery(delivery); // Associa a entrega ao item perdido

            // 3. Criar o registo da interação (OrderItem)
            UserAPP user = userAppService.authenticate();
            OrderItem orderItem = new OrderItem();
            orderItem.setItemLost(itemLost);
            orderItem.setUserAPP(user);
            orderItem.setType(TYPEOFINTERACTION.DELIVERY);
            orderItem.setInteractionDate(Instant.now());
            orderItem.setNotes("Item entregue a " + ownerDto.getName() + " pelo utilizador " + user.getName());

            // Adiciona a nova interação à lista na entidade
            itemLost.getOrderItems().add(orderItem);

            // 4. Salvar tudo (CascadeType.ALL irá salvar Owner e OrderItem)
            itemLost = repository.save(itemLost);

            return new ItemLostDTO(itemLost);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Item perdido não encontrado com o id " + id);
        }
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

    private void copyToDto(ItemLostDTO dto, ItemLost entity){

            entity.setStatus(dto.isStatus());
            entity.setDescription(dto.getDescription());
            entity.setLocation(dto.getLocation());
            entity.setFoundDate(dto.getFoundDate());
            entity.setWhoFind(dto.getWhoFind());
            entity.setImgUrl(dto.getImgUrl());
            // Limpa a lista existente para evitar duplicatas ao atualizar
            entity.getOrderItems().clear();

            for (OrderItemDTO itemDto : dto.getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                // O ID do OrderItem não deve ser definido manualmente,
                // pois geralmente é gerado automaticamente pelo banco de dados.
                // Se houver um usuário associado, ele precisará ser buscado e definido aqui.
                orderItem.setType(itemDto.getType());
                orderItem.setNotes(itemDto.getNotes());
                orderItem.setInteractionDate(itemDto.getInteractionDate());
                orderItem.setItemLost(entity);
                entity.getOrderItems().add(orderItem);
            }
        }

}
