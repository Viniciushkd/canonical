package br.com.project.canonical.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableCaching
@PropertySource("classpath:application.properties")
public class SpringConfiguration {

	public static final String BASE_PACKAGE = "br.com.project.canonical";
}
