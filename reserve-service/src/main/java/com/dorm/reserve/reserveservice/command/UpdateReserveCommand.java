package com.dorm.reserve.reserveservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class UpdateReserveCommand {
    @TargetAggregateIdentifier
    private String aggregateId;
    private String _id;
    private String room_number;
    private String first_name;
    private String last_name;
    private String mobile;
    private String reserve_date;
    private String lease_date;
    private String status;
}
