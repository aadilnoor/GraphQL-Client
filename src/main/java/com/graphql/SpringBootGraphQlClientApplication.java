package com.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootGraphQlClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphQlClientApplication.class, args);
		System.err.println("Application is up...");
	}

}
