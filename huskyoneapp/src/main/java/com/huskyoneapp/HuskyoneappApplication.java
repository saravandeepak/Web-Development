package com.huskyoneapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@EntityScan(basePackages="com.huskyoneapp.entity")
public class HuskyoneappApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HuskyoneappApplication.class);
	}

	
	public static void main(String[] args) {
		SpringApplication.run(HuskyoneappApplication.class, args);
	}
	
}
