package com.dorm.vehicle.vehicleservice.service;

import com.dorm.vehicle.vehicleservice.command.CreateCommandVehicle;
import com.dorm.vehicle.vehicleservice.command.DeleteCommandVehicle;
import com.dorm.vehicle.vehicleservice.command.UpdateCommandVehicle;
import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.core.repository.VehicleRepository;
import com.dorm.vehicle.vehicleservice.query.rest.FindAllVehicleQuery;
import com.dorm.vehicle.vehicleservice.query.rest.FindVehicleByRoomNumberQuery;
import com.dorm.vehicle.vehicleservice.query.rest.VehicleRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    public VehicleService(VehicleRepository vehicleRepository, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.vehicleRepository = vehicleRepository;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public List<VehicleRestModel> getVehicle(){
        FindAllVehicleQuery findAllVehicleQuery = new FindAllVehicleQuery();
        List<VehicleRestModel> vehicles = queryGateway.query(findAllVehicleQuery, ResponseTypes.multipleInstancesOf(VehicleRestModel.class)).join();
//        return vehicleRepository.findAll();
        return vehicles;
    }
    public boolean addVehicle(Vehicle vehicle){
        try {
            System.out.println("Create Service");
            Object id = new ObjectId();
            vehicle.set_id(id.toString());
            CreateCommandVehicle command = CreateCommandVehicle.builder()
                    ._id(vehicle.get_id())
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
                    ._id(vehicle.get_id())
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
            System.out.println("Delete Service");
            DeleteCommandVehicle deleteCommandVehicle = DeleteCommandVehicle.builder()
                    ._id(vehicle.get_id())
                    .license_plate(vehicle.getLicense_plate())
                    .brand(vehicle.getBrand())
                    .color(vehicle.getColor())
                    .room_number(vehicle.getRoom_number())
                    .build();
            commandGateway.sendAndWait(deleteCommandVehicle);
//            vehicleRepository.delete(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<VehicleRestModel> getRoomByNumber(String room_number){
        try {
//            return vehicleRepository.findByRoomNumber(room_number);
            FindVehicleByRoomNumberQuery findVehicleByRoomNumberQuery = new FindVehicleByRoomNumberQuery();
            findVehicleByRoomNumberQuery.setRoomNumber(room_number);
            List<VehicleRestModel> vehicles = queryGateway.query(findVehicleByRoomNumberQuery, ResponseTypes.multipleInstancesOf(VehicleRestModel.class)).join();
            return vehicles;
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
