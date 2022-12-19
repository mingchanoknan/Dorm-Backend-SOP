package com.dorm.vehicle.vehicleservice.core.event;

import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.core.repository.VehicleRepository;
import com.dorm.vehicle.vehicleservice.query.rest.FindAllVehicleQuery;
import com.dorm.vehicle.vehicleservice.query.rest.FindVehicleByRoomNumberQuery;
import com.dorm.vehicle.vehicleservice.query.rest.VehicleRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleQueryHandler {
    private final VehicleRepository vehicleRepository;

    public VehicleQueryHandler(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @QueryHandler
    List<VehicleRestModel> findAllVehicle(FindAllVehicleQuery query){
        System.out.println("query all handler");
        List<VehicleRestModel> vehicleRest = new ArrayList<>();
        List<Vehicle> storedProducts = vehicleRepository.findAll();
        for(Vehicle vehicle: storedProducts){
            VehicleRestModel vehicleRestModel = new VehicleRestModel();
            BeanUtils.copyProperties(vehicle, vehicleRestModel);
            vehicleRest.add(vehicleRestModel);
        }
        return vehicleRest;
    }
    @QueryHandler
    List<VehicleRestModel> findVehicleByRoomNumber(FindVehicleByRoomNumberQuery query){
        System.out.println("query by room number handler");
        List<VehicleRestModel> vehicleRest = new ArrayList<>();
        List<Vehicle> storedProducts = vehicleRepository.findByRoomNumber(query.getRoomNumber());
        for(Vehicle vehicle: storedProducts){
            VehicleRestModel vehicleRestModel = new VehicleRestModel();
            BeanUtils.copyProperties(vehicle, vehicleRestModel);
            vehicleRest.add(vehicleRestModel);
        }
        return vehicleRest;
    }
}
