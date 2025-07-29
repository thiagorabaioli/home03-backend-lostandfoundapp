package tfr.LostAndFoundAPP.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tfr.LostAndFoundAPP.DTO.UserAPPDTO;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAPPService {

    @Autowired
    private UserAPPRepository userAPPRepository;

    public Page<UserAPPDTO> findAll(Pageable pageable) {
            Page<UserAPP> userAPPPage = userAPPRepository.findAll(pageable);
            return userAPPPage.map(x-> new UserAPPDTO(x));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/id")
    public UserAPPDTO findByid(Long id){
        Optional<UserAPP> entity = userAPPRepository.findById(id);
        return entity.map(x -> new UserAPPDTO(x)).orElse(null);
    }

    public UserAPPDTO insert(UserAPPDTO dto){
        UserAPP entity = new UserAPP();
       copyDtoToEntity(dto, entity);
       entity =  userAPPRepository.save(entity);
       return new UserAPPDTO(entity);
    }

    private void copyDtoToEntity(UserAPPDTO dto, UserAPP entity){
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPorNumber(dto.getPorNumber());
        entity.setPassword(dto.getPassword());
    }
}
