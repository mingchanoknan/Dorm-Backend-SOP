package com.dorm.payment.paymentservice.service;

import com.dorm.payment.paymentservice.pojo.Payment;
import com.dorm.payment.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getPayment(){
        return paymentRepository.findAll();
    }
    public boolean addPayment(Payment payment){
        try {
            paymentRepository.insert(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePayment(Payment payment){
        try {
            paymentRepository.save(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deletePayment(Payment payment){
        try {
            paymentRepository.delete(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Payment getRoomByNumber(String room_number){
        try {
            return paymentRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateStatus(Payment payment){
        try {
            paymentRepository.save(payment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Payment> getPaymentById(String id){
        try {
            Optional<Payment> payments = paymentRepository.findById(id);
            return payments;
        }catch (Exception e){
            return null;
        }
    }

    public List<Payment> findPaymentByStatus(String payment_status){
        try {
            return  paymentRepository.findStatus(payment_status);
        }catch (Exception e){
            return null;
        }
    }
}
