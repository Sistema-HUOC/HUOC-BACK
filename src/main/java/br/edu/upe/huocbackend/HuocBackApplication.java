package br.edu.upe.huocbackend;

import br.edu.upe.huocbackend.model.AcessLevel;
import br.edu.upe.huocbackend.model.Administrador;
import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.model.User;
import br.edu.upe.huocbackend.repository.IAdministradorRepository;
import br.edu.upe.huocbackend.repository.IEnfermagemRepository;
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
	@Profile({"prod"})
	public CommandLineRunner devProfile(IUserRepository userRepository, IAdministradorRepository administradorRepository,
										IEnfermagemRepository enfermagemRepository, PasswordEncoder encoder) {
		return args -> {
			if (userRepository.findByEmail("admin@email.com").isEmpty()) {
				administradorRepository.save(new Administrador(
						"Jurema",
						"333.666.777-13",
						"admin@email.com",
						encoder.encode("123"),
						AcessLevel.ADMINISTRADOR
				));
				administradorRepository.save(new Administrador(
						"jair",
						"333.666.777-12",
						"jair@email.com",
						encoder.encode("123"),
						AcessLevel.ADMINISTRADOR
				));
				enfermagemRepository.save(new Enfermagem(
						"victor",
						"333.666.777-14",
						"enfvictor@email.com",
						encoder.encode("123"),
						AcessLevel.ENFERMAGEM,"123456-PE"
				));
				enfermagemRepository.save(new Enfermagem(
						"bianca",
						"333.666.277-14",
						"enfbianca@email.com",
						encoder.encode("123"),
						AcessLevel.ENFERMAGEM,"123256-PE"
				));
			}
		};
	}
}
