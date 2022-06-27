package com.ariel.ApiSocialMedia.Configures;

import java.util.Collections;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api(){
        ApiInfo apiInfo = new ApiInfo(
            "API SOCIAL MEDIA", 
            "AN API ABOUT SOCIAL MEDIA",
            "1.0",
            null,
            new Contact("Ariel Santangelo", "https://github.com/ariel3259", "arielsantangelo610@gmail.com"),
            null,
            null,
            Collections.emptyList());
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo);
    }
}
