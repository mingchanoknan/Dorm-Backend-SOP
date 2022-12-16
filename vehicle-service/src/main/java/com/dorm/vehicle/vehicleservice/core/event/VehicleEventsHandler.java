package com.dorm.vehicle.vehicleservice.core.event;

import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.core.repository.VehicleRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleEventsHandler {

    private VehicleRepository vehicleRepository;

    @Autowired
    public  VehicleEventsHandler(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @EventHandler
    public void on(VehicleCreatedEvent vehicleCreatedEvent) {
        System.out.println("To Mongo Store");
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleCreatedEvent, vehicle);
        vehicleRepository.save(vehicle);
    }
}
