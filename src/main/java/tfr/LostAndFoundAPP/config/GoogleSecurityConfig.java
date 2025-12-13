package tfr.LostAndFoundAPP.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class GoogleSecurityConfig {

    // 1. Injectar o novo serviço de mapeamento de utilizadores
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    @Order(1)
    public SecurityFilterChain googleFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/oauth2/authorization/**", "/login/oauth2/code/**")
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                // 2. Configurar o serviço para mapeamento (NOVO)
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService) 
                )
                // 3. Reverter para a URL de sucesso para o frontend
                .defaultSuccessUrl("https://app.sias.world", true)
            );
        
        return http.build();
    }
}
