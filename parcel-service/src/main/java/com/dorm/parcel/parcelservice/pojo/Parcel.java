package com.dorm.parcel.parcelservice.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Parcel")
public class Parcel implements Serializable {
    @Id
    private String _id;
    private String name;
    private String room_number;
    private String sent_date;
    private String receive_date;
    private String transport_name;
    private String status;

    public Parcel(String _id, String name, String room_number, String sent_date, String receive_date, String transport_name, String status) {
        this._id = _id;
        this.name = name;
        this.room_number = room_number;
        this.sent_date = sent_date;
        this.receive_date = receive_date;
        this.transport_name = transport_name;
        this.status = status;
    }
}
