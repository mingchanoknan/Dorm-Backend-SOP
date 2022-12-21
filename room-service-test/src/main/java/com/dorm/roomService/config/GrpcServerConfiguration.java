package com.dorm.roomService.config;

import com.dorm.roomService.service.RoomServiceImpl;
import com.mongodb.client.MongoClient;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {
    @Autowired
    private RoomServiceImpl roomService; // Autowired ตัว MeterServiceImpl มาใช้

    @Bean // ลอกมาเลย
    public ServerBuilder<?> ServerBuilder(MongoClient mongoClient) {
        System.out.println("Server Pass");
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(50058);

        serverBuilder.addService(roomService);
        return  serverBuilder;
    }
}
