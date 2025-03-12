package gov.fatec.manumanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ManuManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManuManagerApplication.class, args);
	}

}
