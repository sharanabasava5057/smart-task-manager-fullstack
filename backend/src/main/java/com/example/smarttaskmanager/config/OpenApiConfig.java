package com.example.smarttaskmanager.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartTaskManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Task Manager API")
                        .description("Backend APIs for Smart Task Manager project")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your-email@gmail.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://example.com/doc"));
    }
}
