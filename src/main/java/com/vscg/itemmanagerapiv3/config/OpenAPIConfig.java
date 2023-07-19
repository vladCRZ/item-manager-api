package com.vscg.itemmanagerapiv3.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${vscg.openapi.url}")
    private String myUrl;

    @Bean
    public OpenAPI myOpenAPI() {

        Server prodServer = new Server();
        prodServer.setUrl(myUrl);
        prodServer.setDescription("Server URL");

        Contact contact = new Contact();
        contact.setEmail("vlad.crz@gmail.com");
        contact.setName("Sergio Cruz");
        contact.setUrl("https://github.com/vladCRZ");

        Info info = new Info()
                .title("Item Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage items.");

        return new OpenAPI().info(info).servers(List.of(prodServer));
    }
}