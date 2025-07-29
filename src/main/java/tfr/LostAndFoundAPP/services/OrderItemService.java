package tfr.LostAndFoundAPP.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tfr.LostAndFoundAPP.DTO.OrderItemDTO;
import tfr.LostAndFoundAPP.DTO.OwnerDTO;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.entities.Owner;
import tfr.LostAndFoundAPP.repositories.OrderItemRepository;
import tfr.LostAndFoundAPP.repositories.OwnerRepository;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public Page<OrderItemDTO> findAll(Pageable pageable){
        Page<OrderItem> entity = repository.findAll(pageable);
        return entity.map(x -> new OrderItemDTO(x));
    }

    public OrderItemDTO findById(Long id){
        Optional<OrderItem> result = repository.findById(id);
        OrderItem orderItem = result.get();
        OrderItemDTO dot = new OrderItemDTO(orderItem);
        return dot;
    }
}
