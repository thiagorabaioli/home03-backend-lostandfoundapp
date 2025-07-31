package tfr.LostAndFoundAPP.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tfr.LostAndFoundAPP.DTO.ItemLostDTO;
import tfr.LostAndFoundAPP.entities.ItemLost;
import tfr.LostAndFoundAPP.repositories.ItemLostRepository;
import tfr.LostAndFoundAPP.services.exceptions.DatabaseException;
import tfr.LostAndFoundAPP.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class ItemLostService {

    @Autowired
    private ItemLostRepository repository;

    @Transactional(readOnly = true)
    public ItemLostDTO findById(Long id){
        Optional<ItemLost> entity = repository.findById(id);
        return entity.map(x -> new ItemLostDTO(x)).orElseThrow( () -> new ResourceNotFoundException("ItemLost not found with id " + id));

    }

    @Transactional(readOnly = true)
    public Page<ItemLostDTO> findAllPage(Pageable pageable){
        Page<ItemLost> entity = repository.findAll(pageable);
        return entity.map(x -> new ItemLostDTO(x));
    }
    public ItemLostDTO insert(ItemLostDTO dto){
        ItemLost entity = new ItemLost();
        copyToDto(dto, entity);
        entity = repository.save(entity);
        return new ItemLostDTO(entity);

    }

    @Transactional
    public ItemLostDTO update(ItemLostDTO dto, Long id){
        try{
            ItemLost entity = repository.getReferenceById(id);
            copyToDto(dto, entity);
            entity = repository.save(entity);
            return dto;
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("ItemLost not found with id " + id);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Not Found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyToDto(ItemLostDTO dto, ItemLost entity){

        entity.setStatus(dto.isStatus());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setFoundDate(dto.getFoundDate());
        entity.setWhoFind(dto.getWhoFind());
        entity.setImgUrl(dto.getImgUrl());
    }

}
