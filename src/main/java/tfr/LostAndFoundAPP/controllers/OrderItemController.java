package tfr.LostAndFoundAPP.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tfr.LostAndFoundAPP.DTO.OrderItemDTO;
import tfr.LostAndFoundAPP.DTO.OwnerDTO;
import tfr.LostAndFoundAPP.entities.OrderItem;
import tfr.LostAndFoundAPP.services.OrderItemService;
import tfr.LostAndFoundAPP.services.OwnerService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/orderitems")
public class OrderItemController {

    @Autowired
    private OrderItemService service;


    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<OrderItemDTO> findById(@PathVariable  Long id){
       OrderItemDTO dto = service.findById(id);
       return ResponseEntity.ok(dto);

    }
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<Page<OrderItemDTO>> findAllPage(Pageable pageable){
        Page<OrderItemDTO> entity = service.findAll(pageable);
        return ResponseEntity.ok().body(entity);
    }


}
