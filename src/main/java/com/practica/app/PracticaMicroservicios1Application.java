package com.practica.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.practica.*")
@EntityScan("com.practica.entity")
@EnableJpaRepositories("com.practica.repository")
public class PracticaMicroservicios1Application {

	public static void main(String[] args) {
		SpringApplication.run(PracticaMicroservicios1Application.class, args);
	}

}
