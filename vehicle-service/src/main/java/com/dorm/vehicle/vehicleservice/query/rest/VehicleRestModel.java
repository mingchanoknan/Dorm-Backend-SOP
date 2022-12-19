package com.dorm.vehicle.vehicleservice.query.rest;

import lombok.Data;

@Data
public class VehicleRestModel {
    private String _Id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
