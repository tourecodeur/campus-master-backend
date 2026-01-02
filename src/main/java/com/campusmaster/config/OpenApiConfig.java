package com.campusmaster.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI campusMasterOpenAPI() {

        // 1) Déclare le schéma Bearer JWT
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        // 2) Applique la sécurité globalement (fait apparaître "Authorize")
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .info(new Info()
                        .title("API CampusMaster")
                        .description("Backend de la plateforme pédagogique CampusMaster (Master 2)")
                        .version("1.0.0"))
                .schemaRequirement("bearerAuth", bearerScheme)
                .addSecurityItem(securityRequirement);
    }
}
