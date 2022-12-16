package com.example.meter2service.MeterClient;


import com.proto.meter.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeterController {
    @GetMapping("/test")
    public String getTest(@RequestParam String _Id, @RequestParam String room_number, @RequestParam String utilities_type, @RequestParam String monthAndYear, @RequestParam double consumption, @RequestParam double sum, @RequestParam double used_unit) {
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
                .setId(_Id)
                .setRoomNumber(room_number)
                .setUtilitiesType(utilities_type)
                .setMonthAndYear(monthAndYear)
                .setConsumption(consumption)
                .setSum(sum)
                .setUsedUnit((int) used_unit)
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
        channel.shutdown();
        return  meterResponse.getResult();
    }

    @GetMapping("/getAll")
    public Metering getAllMeter() {
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

        // created a protocol buffer greetRequest message
        MeterRequestForAll meterRequest = MeterRequestForAll.newBuilder()
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        MeterResponseForAll meterResponse = meterClient.meterForAll(meterRequest);
        // show the result in GreetResponse message


        System.out.println("Shutting down channel");
        channel.shutdown();
        return  meterResponse.getMetering();
    }
}

