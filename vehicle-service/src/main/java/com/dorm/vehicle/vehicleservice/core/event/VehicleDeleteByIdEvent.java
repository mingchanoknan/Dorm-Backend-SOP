package com.dorm.vehicle.vehicleservice.core.event;

import lombok.Data;

@Data
public class VehicleDeleteByIdEvent {
    private String aggregateId;
    private String _id;
}
