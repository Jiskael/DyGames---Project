package com.DyGames.category_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("API Gateway (local)"),
                        new Server().url("http://localhost:8084").description("Directo al servicio (local)")
                ))
                .info(new Info()
                        .title("API DyGames - Categorías")
                        .version("1.0")
                        .description("Documentación de la API para el microservicio de categorías de videojuegos")
                        .contact(new Contact()
                                .name("Igor Rojas")
                                .email("igo.rojas@duocuc.cl")));
    }
}
