package com.poc.service.engine.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@Profile({"local","dev"})
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.poc.service.engine.rest"))              
          .paths(PathSelectors.any())   
          .build()
          .apiInfo(apiInfo())
          .protocols(new HashSet<>(Arrays.asList("http","https")))      		
          ;
    }
	

	private ApiInfo apiInfo() { 
		return new ApiInfo(
	      "Car APIs",
	      "Car APIs",
	      "1.0",
	      "Enable on Local and Dev only",
	      new Contact("", "", ""),
	      "Hexalite",
	      "");
	}
	
}
