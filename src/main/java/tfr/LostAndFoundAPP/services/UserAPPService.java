package tfr.LostAndFoundAPP.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tfr.LostAndFoundAPP.DTO.UserAPPDTO;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAPPService {

    @Autowired
    private UserAPPRepository repository;

    @Transactional(readOnly = true)
    public Page<UserAPPDTO> findAll(Pageable pageable) {
            Page<UserAPP> userAPPPage = repository.findAll(pageable);
            return userAPPPage.map(x-> new UserAPPDTO(x));
    }

    @Transactional(readOnly = true)
    public UserAPPDTO findByid(Long id){
        Optional<UserAPP> entity = repository.findById(id);
        return entity.map(x -> new UserAPPDTO(x)).orElse(null);
    }

    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @Transactional
    public UserAPPDTO insert(UserAPPDTO dto){
        UserAPP entity = new UserAPP();
       copyDtoToEntity(dto, entity);
       entity =  repository.save(entity);
       return new UserAPPDTO(entity);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public UserAPPDTO update(UserAPPDTO dto, Long id){
            UserAPP entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserAPPDTO(entity);
        }





    private void copyDtoToEntity(UserAPPDTO dto, UserAPP entity){

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPorNumber(dto.getPorNumber());
        entity.setBirthDate(dto.getBirthDate());
    }
}
