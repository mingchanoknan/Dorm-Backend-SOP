package com.dorm.vehicle.vehicleservice.core.rest;

import lombok.Data;

@Data
public class VehicleRestModel {
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
