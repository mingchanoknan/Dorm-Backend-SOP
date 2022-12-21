package com.example.meter2service.MeterServer;

import com.example.meter2service.pojo.Meter;
import com.example.meter2service.service.MeterService;
import com.proto.meter.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Override
    public void meterForAll(MeterRequestForAll request, StreamObserver<MeterResponseForAll> responseObserver) {
        super.meterForAll(request, responseObserver);
    }

    @Override
    public void getByType(GetByTypeRequest request, StreamObserver<GetByTypeResponse> responseObserver) {
        super.getByType(request, responseObserver);
    }


    @Override
    public void getByMonth(GetByMonthRequest request, StreamObserver<GetByMonthResponse> responseObserver) {
        super.getByMonth(request, responseObserver);
    }

    @Override
    public void addMeter(AddMeterRequest request,
                         StreamObserver<AddMeterResponse> responseObserver) {
        String result = "";
        Metering metering = request.getMetering();
        Meter meter = new Meter();

        meter.setRoom_number(metering.getRoomNumber());
        meter.setUtilities_type(metering.getUtilitiesType());
        meter.setMonthAndYear(metering.getMonthAndYear());
        meter.setConsumption(metering.getConsumption());
        meter.setSum(metering.getSum());
        meter.setUsed_unit(metering.getUsedUnit());
        System.out.println("meter : "+meter);
        try {
            String ans = meterService.addMeter(meter);
            System.out.println("ans : " + ans);
            result = ans;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = "Can't add meter";
        }
        // Block 2: create the response message

        System.out.println("result : " + result);
        AddMeterResponse response = AddMeterResponse.newBuilder()
                .setResult(result)
                .build();
        // Block 3: send the response
        responseObserver.onNext(response);
        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }


    @Override
    public void getMeterOfInvoice(GetMeterOfInvoiceRequest request, StreamObserver<GetMeterOfInvoiceResponse> responseObserver) {
        Meter result = meterService.getMeterOfInvoice(request.getRoomNumber(), request.getType(), request.getMonthYear());

        System.out.println("result : " + result);
        Metering metering = Metering.newBuilder()
                .setId(result.get_Id())
                .setRoomNumber(result.getRoom_number())
                .setUtilitiesType(result.getUtilities_type())
                .setMonthAndYear(result.getMonthAndYear())
                .setConsumption(result.getConsumption())
                .setSum(result.getSum())
                .setUsedUnit(result.getUsed_unit())
                .build();

        GetMeterOfInvoiceResponse response = GetMeterOfInvoiceResponse.newBuilder()
                .setMetering(metering)
                .build();
        // Block 3: send the response
        responseObserver.onNext(response);

        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }


    @Override
    public void countPayMeter(CountPayMeterRequest request, StreamObserver<CountPayMeterResponse> responseObserver) {
        List<Meter> result = meterService.getByMonthAndYear(request.getMonthYear(), request.getType());

        System.out.println(result);
        double temp = result.stream().mapToDouble(Meter::getSum).sum();
        CountPayMeterResponse response = CountPayMeterResponse.newBuilder()
                .setNum(temp)
                .build();
            // Block 3: send the response
            responseObserver.onNext(response);

        // Block 4: complete the RPC call
        responseObserver.onCompleted();
    }
}

