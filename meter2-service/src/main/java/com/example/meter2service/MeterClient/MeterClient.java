package com.example.meter2service.MeterClient;


import com.proto.meter.MeterRequest;
import com.proto.meter.MeterResponse;
import com.proto.meter.MeterServiceGrpc;
import com.proto.meter.Metering;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MeterClient {
    public static void main(String[] args) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50058)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");

        //Delete Code
//       DummyServiceGrpc.DummyServiceBlockingStub syncClient
//                = DummyServiceGrpc.newBlockingStub(channel);
        // created a greet service client (blocking - synchronous)
        MeterServiceGrpc.MeterServiceBlockingStub meterClient;
        meterClient = MeterServiceGrpc.newBlockingStub(channel);
        // created a protocol buffer greeting message
        Metering metering = Metering.newBuilder()
                .setId("123")
                .setRoomNumber("E205")
                .setUtilitiesType("Elec")
                .setMonthAndYear("2022")
                .setConsumption(20)
                .setSum(30)
                .setUsedUnit(25)
                .build();
        // created a protocol buffer greetRequest message
        MeterRequest meterRequest = MeterRequest.newBuilder()
                .setMetering(metering)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        MeterResponse meterResponse = meterClient.meter(meterRequest);
        // show the result in GreetResponse message
        System.out.println(meterResponse.getResult());

        System.out.println("Shutting down channel");
//        channel.shutdown();
    }
}


