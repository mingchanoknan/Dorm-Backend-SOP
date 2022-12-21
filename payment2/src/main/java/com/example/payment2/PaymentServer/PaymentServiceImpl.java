package com.example.payment2.PaymentServer;


import com.example.payment2.service.PaymentService;
import com.proto.payments.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.payment2.pojo.Payment;

import java.util.List;


@GrpcService
public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    private PaymentService paymentService;

    public PaymentServiceImpl(){

    }

    @Autowired
    public PaymentServiceImpl(PaymentService paymentService){
        System.out.println("Pass");
        this.paymentService = paymentService;
    }

    @Override
    public void addPayment(AddPaymentRequest paymentRequest, StreamObserver<AddPaymentResponse> paymentResponseStreamObserver){

        String result = "";
        // Block 1: extract the data required
        Paymenting payment = paymentRequest.getPayment();

        // Block 2: create the response message
        Payment payment1 = new Payment();
        payment1.setPayment_date(payment.getPaymentDate());
        payment1.setPayment_time(payment.getPaymentTime());
        payment1.setPayment_note(payment.getPaymentNote());
        payment1.setPayment_status(payment.getPaymentStatus());
        payment1.setIdInvoice(payment.getIdInvoice());
        payment1.setAmount(payment.getAmount());
        payment1.setRoom_number(payment.getRoomNumber());
        payment1.setUrl(payment.getUrl());

        try {
            String addInfo = String.valueOf(paymentService.addPayment(payment1));
            System.out.println("addInfo : " + addInfo);
            result = addInfo;

        } catch (Exception e){
            System.out.println(e.getMessage());
            result = "Can't add Payment";
        }


        AddPaymentResponse response = AddPaymentResponse.newBuilder()
                .setResult(result)
                .build();

        // Block 3: send the response
        paymentResponseStreamObserver.onNext(response);

        // Block 4: complete the RPC call
        paymentResponseStreamObserver.onCompleted();

    }

    @Override
    public void updatePayment(UpdatePaymentRequest paymentRequest, StreamObserver<UpdatePaymentResponse> paymentResponseStreamObserver){

        String result = "";
        // Block 1: extract the data required
        Paymenting payment = paymentRequest.getPayment();

        // Block 2: create the response message
        Payment payment1 = new Payment();
        payment1.set_id(payment.getId());
        payment1.setPayment_date(payment.getPaymentDate());
        payment1.setPayment_time(payment.getPaymentTime());
        payment1.setPayment_note(payment.getPaymentNote());
        payment1.setPayment_status(payment.getPaymentStatus());
        payment1.setIdInvoice(payment.getIdInvoice());
        payment1.setAmount(payment.getAmount());
        payment1.setRoom_number(payment.getRoomNumber());
        payment1.setUrl(payment.getUrl());

        try{
            String updateInfo = String.valueOf(paymentService.updatePayment(payment1));
            System.out.println("updateInfo : " + updateInfo);
            result = updateInfo;
        } catch (Exception e){
            System.out.println(e.getMessage());
            result = "Can't update Payment";
        }


        UpdatePaymentResponse response = UpdatePaymentResponse.newBuilder()
                .setResult(result)
                .build();

        // Block 3: send the response
        paymentResponseStreamObserver.onNext(response);

        // Block 4: complete the RPC call
        paymentResponseStreamObserver.onCompleted();

    }

    @Override
    public void deletePayment(DeletePaymentRequest paymentRequest, StreamObserver<DeletePaymentResponse> paymentResponseStreamObserver){

        String result = "";
        // Block 1: extract the data required
        Paymenting payment = paymentRequest.getPayment();

        // Block 2: create the response message
        Payment payment1 = new Payment();
        payment1.set_id(payment.getId());
        payment1.setPayment_date(payment.getPaymentDate());
        payment1.setPayment_time(payment.getPaymentTime());
        payment1.setPayment_note(payment.getPaymentNote());
        payment1.setPayment_status(payment.getPaymentStatus());
        payment1.setIdInvoice(payment.getIdInvoice());
        payment1.setAmount(payment.getAmount());
        payment1.setRoom_number(payment.getRoomNumber());
        payment1.setUrl(payment.getUrl());

        try{
            String deleteInfo = String.valueOf(paymentService.deletePayment(payment1));
            System.out.println("updateInfo : " + deleteInfo);
            result = deleteInfo;
        } catch (Exception e){
            System.out.println(e.getMessage());
            result = "Can't delete Payment";
        }

        DeletePaymentResponse response = DeletePaymentResponse.newBuilder()
                .setResult(result)
                .build();

        // Block 3: send the response
        paymentResponseStreamObserver.onNext(response);

        // Block 4: complete the RPC call
        paymentResponseStreamObserver.onCompleted();

    }


    @Override
    public void getPaymentById(GetPaymentByIdRequest request, StreamObserver<GetPaymentByIdResponse> responseObserver) {

        // Block 1: extract the data required
        Payment payment = paymentService.getPaymentById(request.getId()).get();

        // Block 2: create the response message
        Paymenting payment1 = Paymenting.newBuilder()
                .setId(payment.get_id())
                .setPaymentDate(payment.getPayment_date())
                .setPaymentTime(payment.getPayment_time())
                .setPaymentNote(payment.getPayment_note())
                .setAmount(payment.getAmount())
                .setRoomNumber(payment.getRoom_number())
                .setPaymentStatus(payment.getPayment_status())
                .setIdInvoice(payment.getIdInvoice())
                .setUrl(payment.getUrl())
                .build();

       GetPaymentByIdResponse response = GetPaymentByIdResponse.newBuilder()
                .setPayment(payment1)
                .build();

        // Block 3: send the response
        responseObserver.onNext(response);

        // Block 4: complete the RPC call
        responseObserver.onCompleted();

    }
}
