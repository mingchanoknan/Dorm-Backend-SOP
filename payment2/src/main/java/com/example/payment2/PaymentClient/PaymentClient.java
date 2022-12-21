package com.example.payment2.PaymentClient;

import com.proto.payments.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class PaymentClient {
    public static void main(String[] args) {
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
                .setId("A123444")
                .setPaymentDate("2022-12-12")
                .setPaymentTime("01:11:11")
                .setPaymentNote("ren meame")
                .setPaymentStatus("checked")
                .setIdInvoice("B12344")
                .setUrl("http234")
                .setAmount(9999)
                .setRoomNumber("A204")
                .build();


        // created a protocol buffer greetRequest message
        AddPaymentRequest addpaymentRequest = AddPaymentRequest.newBuilder()
                .setPayment(payments)
                .build();

        UpdatePaymentRequest updatepaymentRequest = UpdatePaymentRequest.newBuilder()
                .setPayment(payments)
                .build();

        DeletePaymentRequest deletepaymentRequest = DeletePaymentRequest.newBuilder()
                .setPayment(payments)
                .build();

        // call the RPC and get back a GreetResponse (Protocol Buffers)
        AddPaymentResponse addPaymentResponse = paymentClient.addPayment(addpaymentRequest).next();
        UpdatePaymentResponse updatePaymentResponse = paymentClient.updatePayment(updatepaymentRequest).next();
        DeletePaymentResponse deletePaymentResponse = paymentClient.deletePayment(deletepaymentRequest).next();

        // show the result in GreetResponse message
        System.out.println(addPaymentResponse.getResult());
        System.out.println(updatePaymentResponse.getResult());
        System.out.println(deletePaymentResponse.getResult());

        System.out.println("Shutting down channel");
//        channel.shutdown();
    }



}
