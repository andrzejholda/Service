package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Main {
	public static final String LOCALHOST="http://localhost:3000";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public WebMvcConfigurer mvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/users").allowedOrigins(LOCALHOST);
			}
		};
	}

}
