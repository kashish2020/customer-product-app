package com.dxctraining.demoregistryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class DemoregistryappApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoregistryappApplication.class, args);
	}

}
