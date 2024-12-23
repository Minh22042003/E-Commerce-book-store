package com.example.bookebay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = "com.example.bookebay.model")
//@EnableJpaRepositories(basePackages = "com.example.bookebay.repository")
public class BookebayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookebayApplication.class, args);
	}

}
