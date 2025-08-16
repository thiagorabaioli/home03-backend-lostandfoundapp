package tfr.LostAndFoundAPP.controllers;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tfr.LostAndFoundAPP.DTO.entities.DeliveredItemDetailsDTO;
import tfr.LostAndFoundAPP.DTO.entities.ItemLostDTO;
import tfr.LostAndFoundAPP.DTO.entities.ItemLostMinDTO;
import tfr.LostAndFoundAPP.DTO.entities.OwnerDTO;
import tfr.LostAndFoundAPP.services.ItemLostService;
import tfr.LostAndFoundAPP.DTO.entities.BatchDeliveryDTO;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/itemlosts")
public class ItemLostController {

    @Autowired
    private ItemLostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemLostDTO> findById(@PathVariable  Long id){
       ItemLostDTO entity = service.findById(id);
       return ResponseEntity.ok().body(entity);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_VIGILANTE')")
    @GetMapping
    public ResponseEntity<Page<ItemLostDTO>> findAllPage(Pageable  pageable){
        Page<ItemLostDTO> result = service.findAllPage(pageable);
        return  ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_VIGILANTE')")
    @PostMapping
    public ResponseEntity<ItemLostDTO> insertItem(@Valid @RequestBody  ItemLostDTO dto){
        dto = service.insertItem(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_VIGILANTE')")
        @PutMapping(value = "/{id}/deliver")
        public ResponseEntity<ItemLostDTO> deliver(@PathVariable Long id, @Valid @RequestBody OwnerDTO dto) {
            ItemLostDTO updatedDto = service.deliver(id, dto);
            return ResponseEntity.ok().body(updatedDto);
        }


        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @PutMapping(value = "/{id}")
        public ResponseEntity<ItemLostDTO> update(@PathVariable Long id, @Valid @RequestBody ItemLostDTO dto) {
            ItemLostDTO updatedDto = service.update(dto, id);
            return ResponseEntity.ok().body(updatedDto);
        }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable  Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/public")
    public ResponseEntity<List<ItemLostMinDTO>> findPublicItems() {
        List<ItemLostMinDTO> dto = service.findPublicItems();
        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_VIGILANTE')")
    @GetMapping(value = "/delivered")
    public ResponseEntity<List<DeliveredItemDetailsDTO>> findDeliveredItems() {
        List<DeliveredItemDetailsDTO> dto = service.findDeliveredItems();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_VIGILANTE')")
    @PostMapping(value = "/deliver-batch")
    public ResponseEntity<Void> deliverBatch(@Valid @RequestBody BatchDeliveryDTO dto) {
        service.deliverItemsInBatch(dto);
        return ResponseEntity.noContent().build();
    }

}
