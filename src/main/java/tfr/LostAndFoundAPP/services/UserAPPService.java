package tfr.LostAndFoundAPP.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tfr.LostAndFoundAPP.DTO.entities.UserAPPDTO;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;
import tfr.LostAndFoundAPP.services.exceptions.DatabaseException;
import tfr.LostAndFoundAPP.services.exceptions.ResourceNotFoundException;

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
        return entity.map(x -> new UserAPPDTO(x)).orElseThrow( () ->new ResourceNotFoundException("UserAPP not found with id " + id));
    }


    @Transactional
    public UserAPPDTO insert(UserAPPDTO dto){
        UserAPP entity = new UserAPP();
       copyDtoToEntity(dto, entity);
       entity =  repository.save(entity);
       return new UserAPPDTO(entity);
    }

    @Transactional
    public UserAPPDTO update(UserAPPDTO dto, Long id){
        try {
            UserAPP entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserAPPDTO(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("UserAPP not found with id " + id);
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

    private void copyDtoToEntity(UserAPPDTO dto, UserAPP entity){

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPorNumber(dto.getPorNumber());
        entity.setBirthDate(dto.getBirthDate());
    }



    protected UserAPP authenticate() {

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return repository.findByEmail(username).get();

        }
        catch(Exception e){
            throw new UsernameNotFoundException("Invalid username/password");
        }
    }

    @Transactional(readOnly = true)
    public UserAPPDTO getMe(){
        UserAPP user = authenticate();
        return new UserAPPDTO(user);
    }
}
