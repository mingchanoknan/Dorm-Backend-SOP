package com.example.meter2service.MeterServer;


import com.example.meter2service.pojo.Meter;
import com.example.meter2service.service.MeterService;
import com.proto.meter.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@GrpcService
public class MeterServiceImpl  extends MeterServiceGrpc.MeterServiceImplBase {

   private MeterService meterService;

    public MeterServiceImpl() {
    }

    @Autowired
    public MeterServiceImpl(MeterService meterService) {
        System.out.println("Pass");
        this.meterService = meterService;
    }


    @Override
    public void meter(MeterRequest request, StreamObserver<MeterResponse> responseObserver) {
        // Block 1: extract the data required
        Metering metering = request.getMetering();
        String id = metering.getId();
        String room_number = metering.getRoomNumber();
        String utilities_type = metering.getUtilitiesType();
        String monthAndYear = metering.getMonthAndYear();
        double consumption = metering.getConsumption();
        double sum = metering.getSum();
        int used_unit = metering.getUsedUnit();
        // Block 2: create the response message
        List<Meter> meters = meterService.getAllMeter();
        String result = "Hello " + room_number + utilities_type + monthAndYear + "test" + meters;
        MeterResponse response = MeterResponse.newBuilder()
                .setResult(result)
                .build();
        // Block 3: send the response
        responseObserver.onNext(response);
        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }


//    @Override
//    public void meterForAll(MeterRequestForAll request, StreamObserver<MeterResponseForAll> responseObserver) {
//
//        // Block 2: create the response message
//        List<Meter> result = meterRepository.findAll();
//        System.out.println(result);
//        MeterResponseForAll response = MeterResponseForAll.newBuilder()
//                .setMetering(Metering.newBuilder()
//                        .setId(result.get(0).get_Id())
//                        .setRoomNumber(result.get(0).getRoom_number())
//                        .setUtilitiesType(result.get(0).getUtilities_type())
//                        .setMonthAndYear(result.get(0).getMonthAndYear())
//                        .setConsumption(result.get(0).getConsumption())
//                        .setSum(result.get(0).getSum())
//                        .setUsedUnit(result.get(0).getUsed_unit()))
//                .build();
//        // Block 3: send the response
//        responseObserver.onNext(response);
//        // Block 4: complete the RPC call
//        responseObserver.onCompleted();
//    }
}

