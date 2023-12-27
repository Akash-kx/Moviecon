package com.moviecon.RegisterLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RegisterLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterLoginApplication.class, args);
	}

}
