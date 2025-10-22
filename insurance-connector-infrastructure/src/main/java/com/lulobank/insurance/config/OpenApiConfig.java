package com.lulobank.insurance.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI insuranceConnector() {
        return new OpenAPI()
                .info(new Info()
                        .title("Insurance Connector")
                        .version(buildProperties.getVersion())
                        .description("Microservice for insurance offers")
                );
    }
}
