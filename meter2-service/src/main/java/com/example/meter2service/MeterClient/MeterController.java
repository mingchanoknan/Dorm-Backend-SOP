package com.example.meter2service.MeterClient;

import com.example.meter2service.pojo.Meter;
import com.example.meter2service.service.MeterService;
import com.proto.meter.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("meter")
public class MeterController {
    @Autowired
    private MeterService meterService;

    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    @GetMapping("/test")
    public String getTest(@RequestParam String room_number, @RequestParam String utilities_type, @RequestParam String monthAndYear, @RequestParam double consumption, @RequestParam double sum, @RequestParam double used_unit) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50059)
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
        System.out.println("ans :" + meterResponse.getResult());

        System.out.println("Shutting down channel");
        return  meterResponse.getResult();
    }

    @GetMapping("/getall")
    public List<Meter> getAllMeter() {
        List<Meter> meters = meterService.getAllMeter();
        return meters;
    }

    @PostMapping("/add")
    public String addMeter(@RequestBody Meter meter) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50059)
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
                .setRoomNumber(meter.getRoom_number())
                .setUtilitiesType(meter.getUtilities_type())
                .setMonthAndYear(meter.getMonthAndYear())
                .setConsumption(meter.getConsumption())
                .setSum(meter.getSum())
                .setUsedUnit(meter.getUsed_unit())
                .build();
        // created a protocol buffer greetRequest message
        AddMeterRequest meterRequest = AddMeterRequest.newBuilder()
                .setMetering(metering)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        AddMeterResponse meterResponse = meterClient.addMeter(meterRequest);
        // show the result in GreetResponse message
        System.out.println(meterResponse.getResult());

        System.out.println("Shutting down channel");

        return meterResponse.getResult();
    }

//    @GetMapping("/getall")
//    public List<Meter> getAllMeter() {
//        List<Meter> meters = meterService.getAllMeter();
//        return meters;
//    }

    @GetMapping("/getbytype/{type}")
    public List<Meter> getByType(@PathVariable String type) {
        List<Meter> meters = meterService.getByType(type);
        return meters;
    }

    @GetMapping("/getbymonthandyear/{monthandyear}/{type}")
    public List<Meter> getByMonth(@PathVariable String monthandyear, @PathVariable String type) {
        List<Meter> meters = meterService.getByMonthAndYear(monthandyear,type);
        return meters;
    }
//    @PostMapping("/add")
//    public String addMeter(@RequestBody Meter meter) {
//        return meterService.addMeter(meter);
//
//    }

    @RequestMapping(value ="/getMeterInvoice/{room_number}/{type}/{monthYear}", method = RequestMethod.GET)
    public Meter getMeterofInvoice(@PathVariable("room_number") String room_number, @PathVariable("type") String type, @PathVariable("monthYear") String monthYear){
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50059)
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
        GetMeterOfInvoiceRequest meterRequest = GetMeterOfInvoiceRequest.newBuilder()
                .setRoomNumber(room_number)
                .setType(type)
                .setMonthYear(monthYear)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        GetMeterOfInvoiceResponse meterResponse = meterClient.getMeterOfInvoice(meterRequest);
        // show the result in GreetResponse
        Meter meter = new Meter();
        meter.set_Id(meterResponse.getMetering().getId());
        meter.setRoom_number(meterResponse.getMetering().getRoomNumber());
        meter.setUtilities_type(meterResponse.getMetering().getUtilitiesType());
        meter.setMonthAndYear(meterResponse.getMetering().getMonthAndYear());
        meter.setConsumption(meterResponse.getMetering().getConsumption());
        meter.setSum(meterResponse.getMetering().getSum());
        meter.setUsed_unit(meterResponse.getMetering().getUsedUnit());
        System.out.println("ans : "+ meterResponse.getMetering());

        System.out.println("Shutting down channel");
        return meter;
    }

    @GetMapping("/countPayMeter/{monthandyear}/{type}")
    public double countPayMeter(@PathVariable("monthandyear") String monthandyear, @PathVariable("type") String type) {
        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50059)
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
        CountPayMeterRequest meterRequest = CountPayMeterRequest.newBuilder()
                .setMonthYear(monthandyear)
                .setType(type)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        CountPayMeterResponse meterResponse = meterClient.countPayMeter(meterRequest);
        // show the result in GreetResponse message
        System.out.println(meterResponse.getNum());

        System.out.println("Shutting down channel");
        return  meterResponse.getNum();

    }
}
