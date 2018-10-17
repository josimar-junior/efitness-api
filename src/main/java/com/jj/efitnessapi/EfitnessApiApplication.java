package com.jj.efitnessapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.jj.efitnessapi.config.property.EfitnessApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(EfitnessApiProperty.class)
public class EfitnessApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EfitnessApiApplication.class, args);
	}
}
