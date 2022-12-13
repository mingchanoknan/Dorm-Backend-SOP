package com.dorm.meter.meterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MeterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeterServiceApplication.class, args);
	}

}
