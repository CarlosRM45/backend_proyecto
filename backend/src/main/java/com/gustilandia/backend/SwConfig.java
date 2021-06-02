package com.gustilandia.backend;

import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwConfig {
	

	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.gustilandia.backend.controller"))
	      .paths(PathSelectors.any())         
	      .build()
	      .securitySchemes(Lists.newArrayList(apiKey()))
	      .securityContexts(Lists.newArrayList(securityContext()))
	      .apiInfo(apiInfo());
	}
	
	@Bean
	SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.build();
	}
	 
	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
	
	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "Gustilandia Backend", 
	       "Descripción.", 
	       "Versión 1.0", 
	       "Terminos del servicio", 
	       new Contact("IDAT 2021", "https://idat.edu.pe", "a@idat.edu.pe"), 
	       "License of API", "API license URL", Collections.emptyList());
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	//http://localhost:8085/backendgusti/swagger-ui.html#/
	
}

