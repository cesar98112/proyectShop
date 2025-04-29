package api.authentication.controller;

import api.authentication.config.security.user.*;
import api.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/autent")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUserController(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(authenticationService.createUser(userRequest), HttpStatus.CREATED);

    }
    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable(name = "token") String token){

        return authenticationService.validateToken(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserRequest userRequest){
        String token = authenticationService.loginUser(userRequest);


        return new ResponseEntity<>(token,HttpStatus.ACCEPTED);

    }

}
