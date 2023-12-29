package com.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class UpSkillingBloodServiceDiscoveryClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(UpSkillingBloodServiceDiscoveryClientApplication.class, args);
	}

}
