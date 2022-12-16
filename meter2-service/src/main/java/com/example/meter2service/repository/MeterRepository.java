package com.example.meter2service.repository;




import com.example.meter2service.pojo.Meter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterRepository extends MongoRepository<Meter, String> {
    @Query(value = "{utilities_type: '?0'}")
    public List<Meter> findByName(String type);

    @Query("{'room_number' : ?0, 'utilities_type': ?1 ,'monthAndYear' : ?2}")
    public Meter findMeterInvoice(String room_number, String utilities_type, String monthAndYear);

    @Query(value = "{monthAndYear: '?0', utilities_type: '?1'}")
    public List<Meter> findByMonthAndYear(String monthAndYear, String utilities_type);
}


