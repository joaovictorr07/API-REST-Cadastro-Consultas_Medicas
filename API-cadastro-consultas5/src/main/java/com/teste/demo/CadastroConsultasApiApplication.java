package com.teste.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@EntityScan
@SpringBootApplication
@Configuration
@ComponentScan("com.teste.demo")
public class CadastroConsultasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroConsultasApiApplication.class, args);
	}

}
