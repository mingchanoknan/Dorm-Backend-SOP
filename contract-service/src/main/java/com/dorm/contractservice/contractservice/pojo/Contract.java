package com.dorm.contractservice.contractservice.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Contract")
public class Contract implements Serializable {
    @Id
    private String _id;
    private String first_name;
    private String last_name;
    private String address;
    private String phone;
    private String start_date;
    private String end_date;
    private int room_price;
    private String room_number;
    private String room_type;

    private String lease_date;
    private String status;

    public Contract() {
    }

    public Contract(String _id, String first_name, String last_name, String address, String phone, String start_date, String end_date, int room_price, String room_number, String room_type, String lease_date, String status) {
        this._id = _id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone = phone;
        this.start_date = start_date;
        this.end_date = end_date;
        this.room_price = room_price;
        this.room_number = room_number;
        this.room_type = room_type;
        this.lease_date = lease_date;
        this.status = status;
    }
}



