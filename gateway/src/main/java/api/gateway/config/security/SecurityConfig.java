package api.gateway.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;


import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http.cors(corsSpec -> corsSpec.configurationSource(getCorsConfigurationSource())).csrf(csrfSpec -> csrfSpec.disable()).authorizeExchange(
                authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("/api/autent/**","/api/test/**")
                        .permitAll().anyExchange().authenticated()
        ).addFilterAt( jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION).build();
    }
    @Bean
    CorsConfigurationSource getCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "DELETE", "PUT", "POST"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(){
        return new ReactiveAuthenticationManager() {
            @Override
            public Mono<Authentication> authenticate(Authentication authentication) {
                return null;
            }
        };
    }


}
