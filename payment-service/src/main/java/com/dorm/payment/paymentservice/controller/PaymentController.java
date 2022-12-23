package com.dorm.payment.paymentservice.controller;


import com.dorm.payment.paymentservice.pojo.Payment;
import com.dorm.payment.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value ="/payments", method = RequestMethod.GET)
    public List<Payment> getPayment(){
        return paymentService.getPayment();
    }

    @RequestMapping(value ="/addPayment", method = RequestMethod.POST)
    public boolean addPayment(@RequestBody Payment payment){
        try {
            paymentService.addPayment(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/updatePayment", method = RequestMethod.POST)
    public boolean updatePayment(@RequestBody Payment payment){
        try {
            paymentService.updatePayment(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deletePayment", method = RequestMethod.POST)
    public boolean deleteRent(@RequestBody Payment payment){
        try {
            paymentService.deletePayment(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getPaymentNum/{room_number}", method = RequestMethod.GET)
    public Payment getPaymentNum(@PathVariable("room_number") String room_number){
        try {
            Payment payment = paymentService.getRoomByNumber(room_number);
            return payment;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/getPaymentById/{id}", method = RequestMethod.GET)
    public Optional<Payment> getPaymentById(@PathVariable("id") String id){
        try {
            Optional<Payment> payment = paymentService.getPaymentById(id);
            return payment;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/getPaymentStatus/{status}", method = RequestMethod.GET)
    public List<Payment> getPaymentStatus(@PathVariable("status") String status){
        try {
            List<Payment> payment = paymentService.findPaymentByStatus(status);
            return payment;
        }catch (Exception e){
            return null;
        }
    }
}
