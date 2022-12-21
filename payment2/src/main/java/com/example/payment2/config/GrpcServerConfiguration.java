package com.example.payment2.config;

import com.example.payment2.PaymentServer.PaymentServiceImpl;
import com.mongodb.client.MongoClient;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {
    @Autowired
    private PaymentServiceImpl paymentService;

    @Bean
    public ServerBuilder<?> ServerBuilder(MongoClient mongoClient){
        System.out.println("Server Pass");
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(50505);

        serverBuilder.addService(paymentService);
        return  serverBuilder;
    }
}
