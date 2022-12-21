package com.example.meter2service.MeterServer;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MeterServer {

    @Autowired
    private static MeterServiceImpl meterService;

    public static void main(String[] args) {
        System.out.println("Hello gRPC");
        Server server = ServerBuilder.forPort(50058)
                .addService(meterService)
                .build();
        try {
            server.start();
            System.out.println("Server Start");

        }catch (IOException e){
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    System.out.println("Received Shutdown Request");
                    server.shutdown();
                    System.out.println("Successfully Shutdown Server");
                }
        ));

        try{
            server.awaitTermination();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}


