package academy.devjoao.springbootposgre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class SpringbootPosgreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootPosgreApplication.class, args);
	}


}
