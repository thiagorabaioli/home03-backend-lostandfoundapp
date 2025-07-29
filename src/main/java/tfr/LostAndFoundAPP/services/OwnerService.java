package tfr.LostAndFoundAPP.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tfr.LostAndFoundAPP.DTO.OwnerDTO;
import tfr.LostAndFoundAPP.entities.Owner;
import tfr.LostAndFoundAPP.repositories.OwnerRepository;

import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Page<OwnerDTO> findall(Pageable pageable){
        Page<Owner> entity = ownerRepository.findAll(pageable);
        return entity.map(x -> new OwnerDTO(x));
    }

    public OwnerDTO findById(Long id){
        Optional<Owner> entity = ownerRepository.findById(id);
        return entity.map(x->new OwnerDTO(x)).orElse(null);
    }


}
