package api.authentication.service;

import api.authentication.config.security.service.UserDetailServiceImp;
import api.authentication.config.security.user.Roles;
import api.authentication.config.security.user.UserModel;
import api.authentication.config.security.user.UserRequest;
import api.authentication.config.security.user.UserResponse;
import api.authentication.config.security.utils.JwtBuilder;
import api.authentication.config.security.utils.JwtFilter;
import api.authentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtBuilder jwtBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserResponse createUser(UserRequest userRequest){

        UserModel user = createUserWithRoleUser(userRequest);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                null,
                user.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        String token = jwtBuilder.createToken(authentication);

        return new UserResponse(user.getUsername(),token);


    }

    public String loginUser(UserRequest userRequest){

        UserDetails userDetails = userDetailService.loadUserByUsername(userRequest.getUsername());

        if(userDetails == null){
            throw new BadCredentialsException("usuario no encontrado");
        }
        if(!passwordEncoder.matches(userRequest.getPassword(),userDetails.getPassword())){
            throw new BadCredentialsException("contraseña invalida");
        }

         Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        return jwtBuilder.createToken(authentication);

    }

    private UserModel createUserWithRoleUser(UserRequest userRequest){
        return userRepository.save(new UserModel(userRequest.getUsername(),
                bCryptPasswordEncoder.encode(userRequest.getPassword()),
                Set.of(Roles.USER)));


    }
}
