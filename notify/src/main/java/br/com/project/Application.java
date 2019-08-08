package br.com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.project.configuration.SpringConfiguration;

@SpringBootApplication(scanBasePackages = SpringConfiguration.BASE_PACKAGE)
public class Application {
	
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}