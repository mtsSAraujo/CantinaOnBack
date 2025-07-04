package gov.fatec.cantinaOn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CantinaOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(CantinaOnApplication.class, args);
	}

}
