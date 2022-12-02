package br.com.esales.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.esales.user.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class EsalesMicroserviceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsalesMicroserviceUserApplication.class, args);
	}

}
