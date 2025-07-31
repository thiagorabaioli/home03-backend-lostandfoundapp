package tfr.LostAndFoundAPP.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tfr.LostAndFoundAPP.DTO.ItemLostDTO;
import tfr.LostAndFoundAPP.services.ItemLostService;

import java.net.URI;

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

    @GetMapping
    public ResponseEntity<Page<ItemLostDTO>> findAllPage(Pageable  pageable){
        Page<ItemLostDTO> result = service.findAllPage(pageable);
        return  ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<ItemLostDTO> insert( @RequestBody  ItemLostDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemLostDTO> update(@PathVariable  Long id, @RequestBody ItemLostDTO dto){
        dto = service.update(dto,id);
        return ResponseEntity.ok().body(dto);
    }




}
