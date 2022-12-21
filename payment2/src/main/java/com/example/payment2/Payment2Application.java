package com.example.payment2;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableDiscoveryClient
@SpringBootApplication
public class Payment2Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Payment2Application.class, args);
		try {
			Server server = context.getBean(ServerBuilder.class).build().start(); //
			server.awaitTermination();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


}
