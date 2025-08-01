package tfr.LostAndFoundAPP.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfr.LostAndFoundAPP.DTO.entities.OrderItemDTO;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.repositories.OrderItemRepository;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    @Transactional(readOnly = true)
    public Page<OrderItemDTO> findAll(Pageable pageable){
        Page<OrderItem> entity = repository.findAll(pageable);
        return entity.map(x -> new OrderItemDTO(x));
    }

    @Transactional(readOnly = true)
    public OrderItemDTO findById(Long id){
        Optional<OrderItem> result = repository.findById(id);
        OrderItem orderItem = result.get();
        OrderItemDTO dot = new OrderItemDTO(orderItem);
        return dot;
    }
}
