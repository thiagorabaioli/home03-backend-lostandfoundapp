package tfr.LostAndFoundAPP.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tfr.LostAndFoundAPP.DTO.UserAPPDTO;
import tfr.LostAndFoundAPP.services.UserAPPService;

import java.net.URI;

@Controller
@RequestMapping(value = "/userapp")
public class UserAPPController {

    @Autowired
    private UserAPPService service;


    @GetMapping
    public ResponseEntity<Page<UserAPPDTO>> findAllPage(Pageable pageable) {
          Page<UserAPPDTO> result = service.findAll(pageable);
          return  ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<UserAPPDTO> insert (@RequestBody UserAPPDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
