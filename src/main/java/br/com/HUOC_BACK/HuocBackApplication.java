package br.com.HUOC_BACK;

import br.com.HUOC_BACK.Config.Security.TokenService;
import br.com.HUOC_BACK.Users.domain.model.User;
import br.com.HUOC_BACK.Users.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HuocBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuocBackApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner devProfile(UserRepository userRepository, PasswordEncoder encoder){
		return args -> {
				userRepository.save(new User("admin@enail.com",encoder.encode("123")));
		};
	}
}
