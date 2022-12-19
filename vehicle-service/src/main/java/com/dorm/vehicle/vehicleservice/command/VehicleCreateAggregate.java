package com.dorm.vehicle.vehicleservice.command;

import com.dorm.vehicle.vehicleservice.core.event.VehicleCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class VehicleCreateAggregate {
    @AggregateIdentifier
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;

    public VehicleCreateAggregate(){}

    @CommandHandler
    public VehicleCreateAggregate(CreateCommandVehicle createCommandVehicle) {
        System.out.println("Command Handler (create vehicle)");
        VehicleCreatedEvent vehicleCreatedEvent = new VehicleCreatedEvent();
        BeanUtils.copyProperties(createCommandVehicle, vehicleCreatedEvent);
        AggregateLifecycle.apply(vehicleCreatedEvent);
    }


    @EventSourcingHandler
    public void on(VehicleCreatedEvent vehicleCreatedEvent) {
        System.out.println("To Event Store (create vehicle)");
        this._id = vehicleCreatedEvent.get_id();
        this.license_plate = vehicleCreatedEvent.getLicense_plate();
        this.brand = vehicleCreatedEvent.getBrand();
        this.color = vehicleCreatedEvent.getColor();
        this.room_number = vehicleCreatedEvent.getRoom_number();
    }
}
