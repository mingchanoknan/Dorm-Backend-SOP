package com.dorm.reserve.reserveservice.pojo;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Reserve")
public class Reserve implements Serializable {
    @Id
    private String _id;
    private String room_number;
    private String first_name;
    private String last_name;
    private String mobile;
    private String reserve_date;
    private String lease_date;
    private String status;

    public Reserve() {
    }

    public Reserve(String _id, String room_number, String first_name, String last_name, String mobile, String reserve_date, String lease_date, String status) {
        this._id = _id;
        this.room_number = room_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile = mobile;
        this.reserve_date = reserve_date;
        this.lease_date = lease_date;
        this.status = status;
    }
}

