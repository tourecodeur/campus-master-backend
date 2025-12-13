package com.campusmaster.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI campusMasterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API CampusMaster")
                        .description("Backend de la plateforme pédagogique CampusMaster (Master 2)")
                        .version("1.0.0"));
    }
}
