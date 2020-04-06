package br.com.b3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Classe principal da aplicação
 * 
 * @author j.a.vasconcelos
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class CarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
	}
}
