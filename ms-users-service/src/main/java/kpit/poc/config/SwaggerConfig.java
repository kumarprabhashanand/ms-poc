package kpit.poc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket userApi() {
		List<SecurityScheme> securitySchemes = new ArrayList<>();
		securitySchemes.add(new BasicAuth("basicAuth"));
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("kpit.poc.controller"))
				//.paths(regex("/api*"))
				//.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData())
				.securitySchemes(securitySchemes);
	}
	private ApiInfo metaData() {
		ApiInfo apiMeta = new ApiInfo("User API",
				"Performs all operations on user resource",
				"1.0",
				"No terms of service",
				new Contact("Prabhash", "http://www.kpit.com", "kumarprabhaa@kpit.com"),
				"No license",
				"www.google.com");
		return apiMeta;
	}
}