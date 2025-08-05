package com.vc.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VcDashboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VcDashboardServiceApplication.class, args);
	}

}
