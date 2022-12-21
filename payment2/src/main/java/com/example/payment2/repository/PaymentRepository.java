package com.example.payment2.repository;

import com.example.payment2.pojo.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    @Query(value = "{room_number:'?0'}")
    public Payment findByRoomNumber(String room_number);
    @Query(value = "{payment_status:'?0'}")
    public List<Payment> findStatus(String payment_status);
}
