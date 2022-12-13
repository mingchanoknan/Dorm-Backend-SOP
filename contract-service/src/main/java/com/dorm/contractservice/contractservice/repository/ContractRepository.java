package com.dorm.contractservice.contractservice.repository;

import com.dorm.contractservice.contractservice.pojo.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {
    @Query(value = "{room_number:'?0'}")
    public Contract findByRoomNumber(String room_number);
    @Query(value = "{room_number:'?0'}")
    public List<Contract> findByRoomNumStatus(String room_number);
}