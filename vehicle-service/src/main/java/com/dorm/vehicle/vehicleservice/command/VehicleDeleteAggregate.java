package com.dorm.vehicle.vehicleservice.command;

import com.dorm.vehicle.vehicleservice.core.event.VehicleCreatedEvent;
import com.dorm.vehicle.vehicleservice.core.event.VehicleDeletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class VehicleDeleteAggregate {
    @AggregateIdentifier
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;

    public VehicleDeleteAggregate() {}

    @CommandHandler
    public VehicleDeleteAggregate(DeleteCommandVehicle deleteCommandVehicle){
        System.out.println("Delete Command Handler");
        VehicleDeletedEvent vehicleDeletedEvent = new VehicleDeletedEvent();
        BeanUtils.copyProperties(deleteCommandVehicle, vehicleDeletedEvent);
        AggregateLifecycle.apply(vehicleDeletedEvent);
    }

    @EventSourcingHandler
    public void on(VehicleDeletedEvent vehicleDeletedEvent) {
        System.out.println("delete, To event store");
        this._id = vehicleDeletedEvent.get_id();
        this.license_plate = vehicleDeletedEvent.getLicense_plate();
        this.brand = vehicleDeletedEvent.getBrand();
        this.color = vehicleDeletedEvent.getColor();
        this.room_number = vehicleDeletedEvent.getRoom_number();
    }
}
