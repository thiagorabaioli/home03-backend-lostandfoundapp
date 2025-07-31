package tfr.LostAndFoundAPP.controllers;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tfr.LostAndFoundAPP.DTO.entities.DeliveryDTO;
import tfr.LostAndFoundAPP.services.DeliveryService;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeliveryDTO> findById (@PathVariable Long id){
        DeliveryDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryDTO>> findAllPage(Pageable pageable){
        Page<DeliveryDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok().body(dto);
    }
}
