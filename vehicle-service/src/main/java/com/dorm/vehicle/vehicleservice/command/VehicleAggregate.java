package com.dorm.vehicle.vehicleservice.command;

import com.dorm.vehicle.vehicleservice.core.event.VehicleCreatedEvent;
import com.dorm.vehicle.vehicleservice.core.event.VehicleDeleteByIdEvent;
import com.dorm.vehicle.vehicleservice.core.event.VehicleDeletedEvent;
import com.dorm.vehicle.vehicleservice.core.event.VehicleUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class VehicleAggregate {
    @AggregateIdentifier
    private String aggregateId;
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;

    public VehicleAggregate(){}

    @CommandHandler
    public VehicleAggregate(CreateCommandVehicle createCommandVehicle) {
        System.out.println("Command Handler (create vehicle)");
        VehicleCreatedEvent vehicleCreatedEvent = new VehicleCreatedEvent();
        BeanUtils.copyProperties(createCommandVehicle, vehicleCreatedEvent);
        AggregateLifecycle.apply(vehicleCreatedEvent);
    }


    @EventSourcingHandler
    public void on(VehicleCreatedEvent vehicleCreatedEvent) {
        System.out.println("To Event Store (create vehicle)");
        this.aggregateId = vehicleCreatedEvent.getAggregateId();
        this._id = vehicleCreatedEvent.get_id();
        this.license_plate = vehicleCreatedEvent.getLicense_plate();
        this.brand = vehicleCreatedEvent.getBrand();
        this.color = vehicleCreatedEvent.getColor();
        this.room_number = vehicleCreatedEvent.getRoom_number();
    }

    @CommandHandler
    public VehicleAggregate(UpdateCommandVehicle updateCommandVehicle) {
        System.out.println("Update Command Handler");
        VehicleUpdatedEvent vehicleUpdatedEvent = new VehicleUpdatedEvent();
        BeanUtils.copyProperties(updateCommandVehicle, vehicleUpdatedEvent);
        AggregateLifecycle.apply(vehicleUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(VehicleUpdatedEvent vehicleUpdatedEvent) {
        System.out.println("To Event Store (update vehicle)");
        this.aggregateId = vehicleUpdatedEvent.getAggregateId();
        this._id = vehicleUpdatedEvent.get_id();
        this.license_plate = vehicleUpdatedEvent.getLicense_plate();
        this.brand = vehicleUpdatedEvent.getBrand();
        this.color = vehicleUpdatedEvent.getColor();
        this.room_number = vehicleUpdatedEvent.getRoom_number();
    }
    @CommandHandler
    public VehicleAggregate(DeleteCommandVehicle deleteCommandVehicle){
        System.out.println("Delete Command Handler");
        VehicleDeletedEvent vehicleDeletedEvent = new VehicleDeletedEvent();
        BeanUtils.copyProperties(deleteCommandVehicle, vehicleDeletedEvent);
        AggregateLifecycle.apply(vehicleDeletedEvent);
    }

    @EventSourcingHandler
    public void on(VehicleDeletedEvent vehicleDeletedEvent) {
        System.out.println("delete, To event store");
        this.aggregateId = vehicleDeletedEvent.getAggregateId();
        this._id = vehicleDeletedEvent.get_id();
        this.license_plate = vehicleDeletedEvent.getLicense_plate();
        this.brand = vehicleDeletedEvent.getBrand();
        this.color = vehicleDeletedEvent.getColor();
        this.room_number = vehicleDeletedEvent.getRoom_number();
    }

    @CommandHandler
    public VehicleAggregate(DeleteByIdCommandVehicle deleteCommandVehicle){
        System.out.println("Delete by id Command Handler");
        VehicleDeleteByIdEvent vehicleDeletedEvent = new VehicleDeleteByIdEvent();
        BeanUtils.copyProperties(deleteCommandVehicle, vehicleDeletedEvent);
        AggregateLifecycle.apply(vehicleDeletedEvent);
    }

    @EventSourcingHandler
    public void on(VehicleDeleteByIdEvent vehicleDeletedEvent) {
        System.out.println("delete by id, To event store");
        this.aggregateId = vehicleDeletedEvent.getAggregateId();
        this._id = vehicleDeletedEvent.get_id();
    }

}
