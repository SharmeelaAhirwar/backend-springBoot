package com.backend.springBoot.blogApi.Config;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER="Authorization";

	
	private ApiKey apiKey() {
		return new ApiKey("jwt", AUTHORIZATION_HEADER, "header");
	}
	private List<SecurityContext>securityContext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
		
	}
	private List<SecurityReference>sf(){
		
		AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("jwt",  new AuthorizationScope[] {scope}));
	}
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors
				.any())
				.paths(PathSelectors.any()).build();
				
				
		
	}
	public ApiInfo getInfo() {
		return new ApiInfo("blog api:spring boot backend", 
				"this project developed by sharmeela", 
"1.0", "terms and service", new Contact("sharmila", "sharmila urls", 
		"sharmeela@Gmail.com"), "licence of api",
"api licence url",Collections.emptyList());
	}
}
