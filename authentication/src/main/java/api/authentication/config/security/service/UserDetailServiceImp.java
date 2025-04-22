package api.authentication.config.security.service;

import api.authentication.repository.UserRepository;
import api.authentication.config.security.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserModel user = userRepository.findUserModelByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("usuario no encontrado")
        );

        return new User(user.getUsername(),user.getPassword(), user.getAuthorities());


    }
}
