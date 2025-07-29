package tfr.LostAndFoundAPP.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tfr.LostAndFoundAPP.DTO.OwnerDTO;
import tfr.LostAndFoundAPP.services.OwnerService;

@Controller
@RequestMapping(value = "/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<Page<OwnerDTO>> findAll(Pageable pageable){
        Page<OwnerDTO> entity = ownerService.findall(pageable);
        return ResponseEntity.ok().body(entity);
    }

}
