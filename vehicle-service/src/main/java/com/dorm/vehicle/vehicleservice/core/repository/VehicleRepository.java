package com.dorm.vehicle.vehicleservice.core.repository;

import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends
        MongoRepository<Vehicle, String> {
    @Query(value = "{room_number:'?0'}")
    public List<Vehicle> findByRoomNumber(String room_number);
}
