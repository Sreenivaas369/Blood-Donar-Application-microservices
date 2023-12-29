package com.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableHystrix
public class UpSkillingUserServiceDiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpSkillingUserServiceDiscoveryClientApplication.class, args);
	}

}
