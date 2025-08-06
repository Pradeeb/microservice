package com.vc.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.vc.dashboard.client")
@EnableDiscoveryClient
public class VcDashboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcDashboardServiceApplication.class, args);
	}

}
