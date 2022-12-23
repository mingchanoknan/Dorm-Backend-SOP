package com.dorm.reserve.reserveservice.core.event;

import lombok.Data;
@Data
public class ReserveDeleteEvent {
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
