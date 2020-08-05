package com.example.validate.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableRetry
public class ValidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidateApplication.class, args);
	}

}
