package br.edu.upe.huocbackend;

import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.User;
import br.edu.upe.huocbackend.repository.IUserRepository;
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
	@Profile("prod")
	public CommandLineRunner devProfile(IUserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			if (userRepository.findByEmail("admin@email.com").isEmpty()) {
				userRepository.save(new User(
						"Jurema",
						"333.666.777-13",
						"admin@email.com",
						encoder.encode("123"),
						AcessLevel.ADMINISTRATOR
				));
			}
		};
	}

}
