package api.gateway.config.security;


import api.gateway.exeption.CustomExceptions;
import api.gateway.model.UserDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Component
public class JwtFilter implements WebFilter {
    private static final Log log = LogFactory.getLog(JwtFilter.class);


    @Autowired
    private WebClient.Builder webClientConfig;

    private static final List<String> PUBLIC_PATH = List.of("/api/autent","/api/test/");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String token = extractToken(exchange.getRequest());
        String path = exchange.getRequest().getPath().value();


            for(String item:PUBLIC_PATH){
                if(path.contains(item)){
                    return chain.filter(exchange);
                }
            }




            return webClientConfig.build().get().uri("http://authentication/api/autent/validate/"+token).retrieve().bodyToMono(UserDto.class)
                    .flatMap(userDto -> {
                        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userDto.getRoles());
                        Authentication auth = new UsernamePasswordAuthenticationToken(userDto.getUsername(),null, authorities);
                        SecurityContext context = new SecurityContextImpl(auth);

                        return chain.filter(exchange)
                                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
                    }).onErrorResume( exeption->{
                        log.error("Error al validar el token: " + exeption.getMessage());


                        var response = exchange.getResponse();
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");

                        String mensaje = "{\"error\": \"Token inválido o no autorizado\"}";
                        var buffer = response.bufferFactory().wrap(mensaje.getBytes());

                        return response.writeWith(Mono.just(buffer));
                    });



        

        /*ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();

        if(path.contains("auth")){
            log.info("asd");

            return chain.filter(exchange);
        }
        String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if(auth == null){
            return Mono.error(new RuntimeException("dsa"));
        }
        if(!auth.startsWith("Bearer ")){
            return Mono.error(new RuntimeException("EA"));
        };

        String token = auth.replace("Bearer ","");
        exchange.getAttributes().put("token",token);
        log.info("asd-sd");

         */




    }
    private String extractToken(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
