package com.dorm.vehicle.vehicleservice.command;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Builder
public class UpdateCommandVehicle {
    @TargetAggregateIdentifier
    private String _Id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;
}
