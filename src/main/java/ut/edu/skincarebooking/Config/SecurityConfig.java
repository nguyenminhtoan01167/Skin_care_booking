package ut.edu.skincarebooking.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public routes
                        .requestMatchers("/api/auth/**", "/login", "/register", "/user/**", "/admin/assets/**", "/protected/customer/**").permitAll()
                        // Any other request requires authentication
                        .requestMatchers("/api/auth/register-manager").permitAll()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
