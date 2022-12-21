package com.dorm.vehicle.vehicleservice.core.event;

import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.core.repository.VehicleRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleEventsHandler {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleEventsHandler(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @EventHandler
    public void on(VehicleCreatedEvent vehicleCreatedEvent) {
        System.out.println("To Mongo Store (create vehicle)");
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleCreatedEvent, vehicle);
        vehicleRepository.insert(vehicle);
    }

    @EventHandler
    public void on(VehicleUpdatedEvent vehicleUpdatedEvent){
        System.out.println("To Mongo Store (update vehicle)");
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleUpdatedEvent, vehicle);
        vehicleRepository.save(vehicle);
    }
    @EventHandler
    public void on(VehicleDeletedEvent vehicleDeletedEvent) {
        System.out.println("Delete vehicle from Mongo Store");
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDeletedEvent, vehicle);
        vehicleRepository.delete(vehicle);
    }
    @EventHandler
    public void on(VehicleDeleteByIdEvent vehicleDeletedEvent) {
        System.out.println("Delete vehicle by id from Mongo Store");
        vehicleRepository.deleteById(vehicleDeletedEvent.get_id());
    }


}
