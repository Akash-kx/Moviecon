package com.moviecon.InventoryProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryProductApplication.class, args);
	}

}
