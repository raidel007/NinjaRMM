package com.ninjaone.backendinterviewproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class NinjaRmmApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinjaRmmApplication.class, args);
	}

}
