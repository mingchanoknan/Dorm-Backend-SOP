package com.dorm.vehicle.vehicleservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@Builder
public class UpdateCommandVehicle {
    @TargetAggregateIdentifier
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
