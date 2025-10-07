package com.example.ExampleFlyway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Reserva de Equipamentos API", version = "1.0", description = "API para gestão de reservas de equipamentos."))
public class OpenApiConfig {
}