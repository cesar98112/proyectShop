package api.authentication;

import api.authentication.repository.UserRepository;
import api.authentication.config.security.user.Roles;
import api.authentication.config.security.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class AuthenticationApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;



	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if( !userRepository.findAll().isEmpty()){
			userRepository.deleteAll();
		};

		UserModel user = new UserModel("cesar",new BCryptPasswordEncoder().encode("1234"), Set.of(Roles.USER));

		userRepository.save(user);

	}
}
