package com.dorm.vehicle.vehicleservice.controller;

import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import com.dorm.vehicle.vehicleservice.query.rest.VehicleRestModel;
import com.dorm.vehicle.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.GET)
    public List<VehicleRestModel> getVehicle(){
        return vehicleService.getVehicle();
    }

    @RequestMapping(value ="/addVehicle", method = RequestMethod.POST)
    public boolean addVehicle(@RequestBody Vehicle vehicle){
        try {
            vehicleService.addVehicle(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/updateVehicle", method = RequestMethod.PUT)
    public boolean updateVehicle(@RequestBody Vehicle vehicle){
        try {
            vehicleService.updateVehicle(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deleteVehicle", method = RequestMethod.DELETE)
    public boolean deleteVehicle(@RequestBody Vehicle vehicle){
        try {
            vehicleService.deleteVehicle(vehicle);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getVehicleNum/{room_number}", method = RequestMethod.GET)
    public List<VehicleRestModel> getVehicleNum(@PathVariable("room_number") String room_number){
        try {
            List<VehicleRestModel> vehicle = vehicleService.getRoomByNumber(room_number);
            return vehicle;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/deleteAllVehicle/{ids}", method = RequestMethod.DELETE)
    public boolean deleteAllVehicle(@PathVariable("ids") List<String> ids){
        try {
            ids.forEach( d -> {
                 vehicleService.deleteAllVehicle(d);
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
