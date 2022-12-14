package com.dorm.vehicle.vehicleservice.core.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class VehicleCreatedEvent {
    private String aggregateId;
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
