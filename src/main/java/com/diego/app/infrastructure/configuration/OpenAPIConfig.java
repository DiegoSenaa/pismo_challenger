package com.diego.app.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("diego.senaa@gmail.com");
        contact.setName("Diego Sena");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("A Account transaction API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to simulate a transaction context.")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}