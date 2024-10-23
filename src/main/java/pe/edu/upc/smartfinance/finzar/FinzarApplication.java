package pe.edu.upc.smartfinance.finzar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinzarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinzarApplication.class, args);
	}

}
