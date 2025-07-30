package tfr.LostAndFoundAPP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import tfr.LostAndFoundAPP.entities.Role;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.projections.UserDetailsProjection;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserAPPRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if(result.size() ==0 ){
            throw new UsernameNotFoundException("User not found");
        }
        UserAPP userapp = new UserAPP();
        userapp.setEmail(username);
        userapp.setPassword(result.get(0).getPassword());
        for(UserDetailsProjection projection : result){
            userapp.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return userapp;
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
}
