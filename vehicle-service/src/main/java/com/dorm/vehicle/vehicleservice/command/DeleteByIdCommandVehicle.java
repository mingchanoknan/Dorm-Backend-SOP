package com.dorm.vehicle.vehicleservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Builder
@Data
public class DeleteByIdCommandVehicle {
    @TargetAggregateIdentifier
    private String aggregateId;
    private String _id;
}
