package com.dorm.reserve.reserveservice.command;

import com.dorm.reserve.reserveservice.core.event.ReserveCreateEvent;
import com.dorm.reserve.reserveservice.core.event.ReserveDeleteEvent;
import com.dorm.reserve.reserveservice.core.event.ReserveUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ReserveAggregate {
    @AggregateIdentifier
    private String aggregateId;
    private String _id;
    private String room_number;
    private String first_name;
    private String last_name;
    private String mobile;
    private String reserve_date;
    private String lease_date;
    private String status;
    public ReserveAggregate() {
    }

    @CommandHandler
    public ReserveAggregate(CreateReserveCommand createReserveCommand) {
        System.out.println("Create Reserve Command Handler");
        ReserveCreateEvent reserveCreateEvent = new ReserveCreateEvent();
        BeanUtils.copyProperties(createReserveCommand,reserveCreateEvent);
        AggregateLifecycle.apply(reserveCreateEvent);

    }
    @EventSourcingHandler
    public void on(ReserveCreateEvent reserveCreateEvent){
        System.out.println("Event Sourcing Handler (add)");
        this.aggregateId = reserveCreateEvent.getAggregateId();
        this._id = reserveCreateEvent.get_id();
        this.room_number = reserveCreateEvent.getRoom_number();
        this.first_name = reserveCreateEvent.getFirst_name();
        this.last_name = reserveCreateEvent.getLast_name();
        this.mobile = reserveCreateEvent.getMobile();
        this.reserve_date = reserveCreateEvent.getReserve_date();
        this.lease_date = reserveCreateEvent.getLease_date();
        this.status = reserveCreateEvent.getStatus();
    }

    @CommandHandler
    public ReserveAggregate(UpdateReserveCommand updateReserveCommand) {
        System.out.println("update Reserve Command Handler");
        ReserveUpdateEvent reserveUpdateEvent = new ReserveUpdateEvent();
        BeanUtils.copyProperties(updateReserveCommand,reserveUpdateEvent);
        AggregateLifecycle.apply(reserveUpdateEvent);
    }
    @EventSourcingHandler
    public void on(ReserveUpdateEvent event){
        System.out.println("Event Sourcing Handler (update)");
        this.aggregateId = event.getAggregateId();
        this._id = event.get_id();
        this.room_number = event.getRoom_number();
        this.first_name = event.getFirst_name();
        this.last_name = event.getLast_name();
        this.mobile = event.getMobile();
        this.reserve_date = event.getReserve_date();
        this.lease_date = event.getLease_date();
        this.status = event.getStatus();
    }
    @CommandHandler
    public ReserveAggregate(DeleteReserveCommand deleteReserveCommand) {
        System.out.println("Delete Reserve Command Handler");
        ReserveDeleteEvent reserveDeleteEvent = new ReserveDeleteEvent();
        BeanUtils.copyProperties(deleteReserveCommand, reserveDeleteEvent);
        AggregateLifecycle.apply(reserveDeleteEvent);
    }
    @EventSourcingHandler
    public void on(ReserveDeleteEvent event){
        System.out.println("Event Sourcing Handler (Delete)");
        this.aggregateId = event.getAggregateId();
        this._id = event.get_id();
        this.room_number = event.getRoom_number();
        this.first_name = event.getFirst_name();
        this.last_name = event.getLast_name();
        this.mobile = event.getMobile();
        this.reserve_date = event.getReserve_date();
        this.lease_date = event.getLease_date();
        this.status = event.getStatus();
    }
}
