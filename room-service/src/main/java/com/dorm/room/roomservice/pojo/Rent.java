package com.dorm.room.roomservice.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("RoomForRent")
public class Rent implements Serializable {
    @Id
    private String _id;
    private String room_number;
    private String floor;
    private String build;
    private int room_price;
    private String room_type;
    private int common_fee;
    private String room_status;

    public Rent() {
    }

    public Rent(String _id, String room_number, String floor, String build, int room_price, String room_type, int common_fee, String room_status) {
        this._id = _id;
        this.room_number = room_number;
        this.floor = floor;
        this.build = build;
        this.room_price = room_price;
        this.room_type = room_type;
        this.common_fee = common_fee;
        this.room_status = room_status;
    }
}

