package tfr.LostAndFoundAPP.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tfr.LostAndFoundAPP.DTO.entities.UserAPPDTO;
import tfr.LostAndFoundAPP.services.UserAPPService;

import java.net.URI;

@Controller
@RequestMapping(value = "/userapp")
public class UserAPPController {

    @Autowired
    private UserAPPService service;


    @Autowired
    private HandlerMapping resourceHandlerMapping;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'VIGILANTE')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserAPPDTO> getMe(){
        UserAPPDTO dto  = service.getMe();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'VIGILANTE')")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserAPPDTO> findByid(@PathVariable Long id){
        UserAPPDTO dto = service.findByid(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<UserAPPDTO>> findAllPage(Pageable pageable) {
          Page<UserAPPDTO> result = service.findAll(pageable);
          return  ResponseEntity.ok().body(result);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UserAPPDTO> insert (@Valid @RequestBody UserAPPDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<UserAPPDTO> update(@Valid @RequestBody UserAPPDTO dto, @PathVariable  Long id) {
          dto = service.update(dto, id);
          return ResponseEntity.ok().body(dto);
    }

}
