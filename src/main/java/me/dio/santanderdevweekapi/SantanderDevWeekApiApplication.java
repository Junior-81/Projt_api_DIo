package me.dio.santanderdevweekapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		title = "Santander Dev Week 2023 API",
		description = "API RESTful criada para o desafio da Santander Dev Week 2023",
		version = "v1"
	),
	servers = {
		@Server(url = "http://localhost:8080", description = "Servidor de Desenvolvimento"),
		@Server(url = "https://sdw-2023-prd.up.railway.app", description = "Servidor de Produção")
	}
)
@SpringBootApplication
public class SantanderDevWeekApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderDevWeekApiApplication.class, args);
	}
}
