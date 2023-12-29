package com.in;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
//@EnableCaching
public class UpSkillingItemServiceDiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpSkillingItemServiceDiscoveryClientApplication.class, args);
	}

}
