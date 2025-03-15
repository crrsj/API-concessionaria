package br.com.concessionaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API - Concessionária.",
			version = "1.0",
			description = "API para gerenciamento de concessionária .",
			contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
	)
public class ConcessionariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcessionariaApplication.class, args);
	}

}
