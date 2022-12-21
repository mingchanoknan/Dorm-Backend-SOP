package com.example.payment2.PaymentClient;


import com.example.payment2.pojo.Payment;
import com.proto.payments.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @GetMapping("/add")
    public String addPayment(@RequestParam String payment_date, @RequestParam String payment_time,@RequestParam String payment_note,
                          @RequestParam String room_number, @RequestParam String idInvoice,@RequestParam String url,
                          @RequestParam String payment_status, @RequestParam double amount) {

        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50505)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");

        // created a greet service client (blocking - synchronous)
        PaymentServiceGrpc.PaymentServiceBlockingStub paymentClient;
        paymentClient = PaymentServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message
        Paymenting payments = Paymenting.newBuilder()
                .setPaymentDate(payment_date)
                .setPaymentTime(payment_time)
                .setPaymentNote(payment_note)
                .setPaymentStatus(payment_status)
                .setUrl(url)
                .setAmount(amount)
                .setRoomNumber(room_number)
                .setIdInvoice(idInvoice)
                .build();

        // created a protocol buffer greetRequest message
        AddPaymentRequest addpaymentRequest = AddPaymentRequest.newBuilder()
                .setPayment(payments)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        AddPaymentResponse addPaymentResponse = paymentClient.addPayment(addpaymentRequest).next();
        // show the result in GreetResponse message
        System.out.println(addPaymentResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();

        return addPaymentResponse.getResult();
    }

    @GetMapping("/update")
    public String updatePayment(@RequestParam String payment_date, @RequestParam String payment_time,@RequestParam String payment_note,
                          @RequestParam String room_number, @RequestParam String idInvoice,@RequestParam String url,
                          @RequestParam String payment_status, @RequestParam double amount, @RequestParam String _id) {

        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50505)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");

        // created a greet service client (blocking - synchronous)
        PaymentServiceGrpc.PaymentServiceBlockingStub paymentClient;
        paymentClient = PaymentServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message
        Paymenting payments = Paymenting.newBuilder()
                .setPaymentDate(payment_date)
                .setPaymentTime(payment_time)
                .setPaymentNote(payment_note)
                .setPaymentStatus(payment_status)
                .setUrl(url)
                .setAmount(amount)
                .setRoomNumber(room_number)
                .setIdInvoice(idInvoice)
                .setId(_id)
                .build();

        // created a protocol buffer greetRequest message
        UpdatePaymentRequest updatepaymentRequest = UpdatePaymentRequest.newBuilder()
                .setPayment(payments)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        UpdatePaymentResponse updatePaymentResponse = paymentClient.updatePayment(updatepaymentRequest).next();
        // show the result in GreetResponse message
        System.out.println(updatePaymentResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();

        return updatePaymentResponse.getResult();
    }

    @GetMapping("/delete")
    public String deletePayment(@RequestParam String payment_date, @RequestParam String payment_time,@RequestParam String payment_note,
                                @RequestParam String room_number, @RequestParam String idInvoice,@RequestParam String url,
                                @RequestParam String payment_status, @RequestParam double amount) {

        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50505)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");

        // created a greet service client (blocking - synchronous)
        PaymentServiceGrpc.PaymentServiceBlockingStub paymentClient;
        paymentClient = PaymentServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message
        Paymenting payments = Paymenting.newBuilder()
                .setPaymentDate(payment_date)
                .setPaymentTime(payment_time)
                .setPaymentNote(payment_note)
                .setPaymentStatus(payment_status)
                .setUrl(url)
                .setAmount(amount)
                .setRoomNumber(room_number)
                .setIdInvoice(idInvoice)
                .build();

        // created a protocol buffer greetRequest message
        DeletePaymentRequest deletepaymentRequest = DeletePaymentRequest.newBuilder()
                .setPayment(payments)
                .build();
        // call the RPC and get back a GreetResponse (Protocol Buffers)
        DeletePaymentResponse deletePaymentResponse = paymentClient.deletePayment(deletepaymentRequest).next();
        // show the result in GreetResponse message
        System.out.println(deletePaymentResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();

        return deletePaymentResponse.getResult();
    }


    @GetMapping("/getByNumber")
    public Payment getPaymentById(@RequestParam String id) {

        System.out.println("Hello gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50505)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");

        // created a greet service client (blocking - synchronous)
        PaymentServiceGrpc.PaymentServiceBlockingStub paymentClient;
        paymentClient = PaymentServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message

        // created a protocol buffer greetRequest message
        GetPaymentByIdRequest getPaymentByIdRequest =  GetPaymentByIdRequest.newBuilder()
                .setId(id)
                .build();

        // call the RPC and get back a GreetResponse (Protocol Buffers)
        GetPaymentByIdResponse getPaymentByIdResponse = paymentClient.getPaymentById( getPaymentByIdRequest).next();

        Payment payment1 = new Payment();
        payment1.set_id(getPaymentByIdResponse.getPayment().getId());
        payment1.setPayment_date(getPaymentByIdResponse.getPayment().getPaymentDate());
        payment1.setPayment_time(getPaymentByIdResponse.getPayment().getPaymentTime());
        payment1.setPayment_note(getPaymentByIdResponse.getPayment().getPaymentNote());
        payment1.setPayment_status(getPaymentByIdResponse.getPayment().getPaymentStatus());
        payment1.setIdInvoice(getPaymentByIdResponse.getPayment().getIdInvoice());
        payment1.setAmount(getPaymentByIdResponse.getPayment().getAmount());
        payment1.setRoom_number(getPaymentByIdResponse.getPayment().getRoomNumber());
        payment1.setUrl(getPaymentByIdResponse.getPayment().getUrl());


        // show the result in GreetResponse message
        System.out.println(getPaymentByIdResponse.getPayment());

        System.out.println("Shutting down channel");
        channel.shutdown();

        return payment1;
    }

}
