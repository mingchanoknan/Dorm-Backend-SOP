package com.dorm.vehicle.vehicleservice.core.event;

import lombok.Data;

@Data
public class VehicleDeletedEvent {
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
