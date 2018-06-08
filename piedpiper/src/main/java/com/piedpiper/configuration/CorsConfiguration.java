package com.piedpiper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS支持
 * 
 * @author homeless2010
 */
//@Configuration
public class CorsConfiguration {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/web/*/**").allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS");
				// registry.addMapping("/_web/_apps/*/api/**").allowedMethods("GET",
				// "POST", "DELETE", "PUT", "OPTIONS");
				// registry.addMapping("/_web/_apps/*/api/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
