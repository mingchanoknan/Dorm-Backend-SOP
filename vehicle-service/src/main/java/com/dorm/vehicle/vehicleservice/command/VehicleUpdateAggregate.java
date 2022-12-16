package com.dorm.vehicle.vehicleservice.command;

import com.dorm.vehicle.vehicleservice.core.event.VehicleCreatedEvent;
import com.dorm.vehicle.vehicleservice.core.event.VehicleUpdatedEvent;
import com.dorm.vehicle.vehicleservice.core.pojo.Vehicle;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class VehicleUpdateAggregate {
    @AggregateIdentifier
    private String _Id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;

    public VehicleUpdateAggregate() {

    }
    @CommandHandler
    public VehicleUpdateAggregate(UpdateCommandVehicle updateCommandVehicle) {
        VehicleUpdatedEvent vehicleUpdatedEvent = new VehicleUpdatedEvent();
        BeanUtils.copyProperties(updateCommandVehicle, vehicleUpdatedEvent);
        AggregateLifecycle.apply(vehicleUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(VehicleUpdatedEvent vehicleUpdatedEvent) {
        this._Id = vehicleUpdatedEvent.get_Id();
        this.license_plate = vehicleUpdatedEvent.getLicense_plate();
        this.brand = vehicleUpdatedEvent.getBrand();
        this.color = vehicleUpdatedEvent.getColor();
        this.room_number = vehicleUpdatedEvent.getRoom_number();
    }
}
