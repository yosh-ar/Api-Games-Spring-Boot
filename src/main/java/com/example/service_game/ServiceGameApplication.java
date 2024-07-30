package com.example.service_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ServiceGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGameApplication.class, args);
	}

}
