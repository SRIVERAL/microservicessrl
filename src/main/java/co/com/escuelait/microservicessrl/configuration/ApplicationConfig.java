package co.com.escuelait.microservicessrl.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
@ToString
public class ApplicationConfig {
	
	private String name;
	
	private Integer year;
	
	private String edition;
	
	private String[] countries;
	
    @Value("{JAVA_HOME}")
	private String javaHome;
}
