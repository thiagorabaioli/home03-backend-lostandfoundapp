package tfr.LostAndFoundAPP.config;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import tfr.LostAndFoundAPP.entities.Role;
import tfr.LostAndFoundAPP.entities.UserAPP;
import tfr.LostAndFoundAPP.repositories.RoleRepository;
import tfr.LostAndFoundAPP.repositories.UserAPPRepository;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserAPPRepository userAPPRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        // 1. Usa o serviço padrão do Spring para obter os dados do Google
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        
        // 2. Extrair informações essenciais
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        // O ID do Google é usado como o "name" do principal no Spring
        String providerId = oauth2User.getName(); 
        
        // 3. Buscar ou criar utilizador no BD local (Mapeamento)
        UserAPP user = userAPPRepository.findByEmail(email).orElseGet(() -> {
            // Se o utilizador não existir, cria um novo
            UserAPP newUser = new UserAPP();
            newUser.setEmail(email);
            newUser.setName(name);
            // É útil guardar o ID do Google para futuras verificações (Assumindo que existe este campo em UserAPP)
            // Caso não tenha providerId em UserAPP, remova a linha abaixo.
            // newUser.setProviderId(providerId); 
            
            // Atribuir o papel padrão: ROLE_VISITANTE (ID 3 no seu import.sql)
            Optional<Role> defaultRole = roleRepository.findByAuthority("ROLE_VISITANTE");
            defaultRole.ifPresent(role -> newUser.getRoles().add(role));
            
            // Salvar no BD
            return userAPPRepository.save(newUser);
        });
        
        // 4. Mapear roles locais para authorities do Spring Security
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
        
        // 5. Devolver um OAuth2User que o Spring Security consegue processar
        return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
    }
}
