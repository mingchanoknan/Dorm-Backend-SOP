package com.dorm.vehicle.vehicleservice.service;

import com.dorm.vehicle.vehicleservice.command.CreateCommandVehicle;
import com.dorm.vehicle.vehicleservice.command.UpdateCommandVehicle;
import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.core.repository.VehicleRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public VehicleService(VehicleRepository vehicleRepository, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.vehicleRepository = vehicleRepository;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public List<Vehicle> getVehicle(){
        return vehicleRepository.findAll();
    }
    public boolean addVehicle(Vehicle vehicle){
        try {
            System.out.println("Create Service");
            Object id = new ObjectId();
            vehicle.set_Id(id.toString());
            CreateCommandVehicle command = CreateCommandVehicle.builder()
                    ._Id(vehicle.get_Id())
                    .license_plate(vehicle.getLicense_plate())
                    .brand(vehicle.getBrand())
                    .color(vehicle.getColor())
                    .room_number(vehicle.getRoom_number())
                    .build();

            commandGateway.sendAndWait(command);
//            vehicleRepository.insert(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateVehicle(Vehicle vehicle){
        try {
            System.out.println("Update Service");
            UpdateCommandVehicle updateCommandVehicle = UpdateCommandVehicle.builder()
                    ._Id(vehicle.get_Id())
                    .license_plate(vehicle.getLicense_plate())
                    .brand(vehicle.getBrand())
                    .color(vehicle.getColor())
                    .room_number(vehicle.getRoom_number())
                    .build();
            commandGateway.sendAndWait(updateCommandVehicle);
//            vehicleRepository.save(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteVehicle(Vehicle vehicle){
        try {
            vehicleRepository.delete(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Vehicle> getRoomByNumber(String room_number){
        try {
            return vehicleRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public boolean deleteAllVehicle(String id){
        try {
            vehicleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
