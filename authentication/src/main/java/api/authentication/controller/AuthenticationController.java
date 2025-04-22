package api.authentication.controller;

import api.authentication.config.security.user.UserRequest;
import api.authentication.config.security.user.UserResponse;
import api.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUserController(@RequestBody UserRequest userRequest){

        return new ResponseEntity<>(authenticationService.createUser(userRequest), HttpStatus.CREATED);

    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserRequest userRequest){
        String token = authenticationService.loginUser(userRequest);


        return new ResponseEntity<>(token,HttpStatus.ACCEPTED);

    }

}
