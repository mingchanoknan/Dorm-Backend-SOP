package com.dorm.vehicle.vehicleservice.core.event;

import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.core.repository.VehicleRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VehicleDeleteEventHandler {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public  VehicleDeleteEventHandler(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @EventHandler
    public void on(VehicleDeletedEvent vehicleDeletedEvent) {
        System.out.println("Delete vehicle from Mongo Store");
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDeletedEvent, vehicle);
        vehicleRepository.delete(vehicle);
    }
}
