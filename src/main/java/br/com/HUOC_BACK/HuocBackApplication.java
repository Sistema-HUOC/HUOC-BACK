package br.com.HUOC_BACK;

import br.com.HUOC_BACK.model.User;
import br.com.HUOC_BACK.repository.IUserRepository;
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
	public CommandLineRunner devProfile(IUserRepository IUserRepository, PasswordEncoder encoder){
		return args -> {
				IUserRepository.save(new User("admin@email.com",encoder.encode("123")));
		};
	}
}
