package com.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class UpSkillingShopServiceDiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpSkillingShopServiceDiscoveryClientApplication.class, args);
	}

}
