package sel.catalogue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) {
        return http
                .authorizeHttpRequests((req -> req
                        .anyRequest().permitAll()))
//                        .requestMatchers(HttpMethod.POST, "/catalogue-api/products")
//                        .hasAuthority("SCOPE_edit_catalogue")
//                        .requestMatchers(HttpMethod.PUT, "/catalogue-api/products/{id}")
//                        .hasAuthority("SCOPE_edit_catalogue")
//                        .requestMatchers(HttpMethod.DELETE, "/catalogue-api/products/{id}")
//                        .hasAuthority("SCOPE_edit_catalogue")
//                        .requestMatchers(HttpMethod.GET)
//                        .hasAuthority("SCOPE_view_catalogue")
//                        .anyRequest()
//                        .denyAll()))
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}
