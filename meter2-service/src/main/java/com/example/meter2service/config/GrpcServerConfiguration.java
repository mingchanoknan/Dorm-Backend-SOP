package com.example.meter2service.config;


import com.example.meter2service.MeterServer.MeterServiceImpl;
import com.mongodb.client.MongoClient;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {

    @Autowired
    private MeterServiceImpl meterService;

    @Bean
    public ServerBuilder<?> ServerBuilder(MongoClient mongoClient) {
        System.out.println("Server Pass");
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(50059);

        serverBuilder.addService(meterService);
        return  serverBuilder;
    }

}
