package com.app.olx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.app.olx"))
				.build();
	}

	
	private ApiInfo apiInfo() {
		return new ApiInfo("OLX Login REST API Documentation"
				, "This API designed for olx login"
				, "1.0.0"
				, "http://www.zensar.com"
				, new Contact("Vijay Jejurkar", "https://gpl.com", "vijay.jejurkar@zensar.com")
				, "GPL"
				, "https://gpl.com", new ArrayList());
	}
}

