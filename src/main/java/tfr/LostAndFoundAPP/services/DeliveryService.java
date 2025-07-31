package tfr.LostAndFoundAPP.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tfr.LostAndFoundAPP.DTO.DeliveryDTO;
import tfr.LostAndFoundAPP.entities.Delivery;
import tfr.LostAndFoundAPP.repositories.DeliveryRepository;
import tfr.LostAndFoundAPP.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository repository;

    @Transactional(readOnly = true)
    public DeliveryDTO findById(Long id){
        Optional<Delivery> entity = repository.findById(id);
        return entity.map(x -> new DeliveryDTO(x)).orElseThrow( () -> new ResourceNotFoundException("Delivery not found with id " + id));
    }

    @Transactional(readOnly = true)
    public Page<DeliveryDTO> findAll(Pageable pageable){
        Page<Delivery> entity = repository.findAll(pageable);
        return entity.map(x -> new DeliveryDTO(x));
    }
}
