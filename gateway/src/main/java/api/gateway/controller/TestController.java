package api.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private WebClient.Builder webClientConfig;




    @GetMapping("/token")
    public Mono<String> validateTest(){
       return Mono.just("hola");




    }

}
