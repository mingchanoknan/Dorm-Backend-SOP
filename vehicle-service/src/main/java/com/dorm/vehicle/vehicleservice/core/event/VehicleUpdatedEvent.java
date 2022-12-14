package com.dorm.vehicle.vehicleservice.core.event;

import lombok.Data;

@Data
public class VehicleUpdatedEvent {
    private String aggregateId;
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
