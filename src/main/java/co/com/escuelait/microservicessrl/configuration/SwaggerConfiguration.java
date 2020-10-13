package co.com.escuelait.microservicessrl.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfo("EscuelaIT", "MIcroservice", "1.0", "http://escuelait.com",
						new Contact("Santiago Rivera Loaiza", "escuelait.com", "sriveraloaiza@gmail.com"),
						"Uso exclusivo de Santiago Rivera", "http://anfix.com", Collections.emptyList()));
		
		
	}
	
}
