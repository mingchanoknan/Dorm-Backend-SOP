package com.dorm.vehicle.vehicleservice.core.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Vehicle")
public class Vehicle implements Serializable {
    @Id
    private String _id;
    private String license_plate;
    private String color;
    private String brand;
    private String room_number;

    public Vehicle() {
    }

    public Vehicle(String _id, String license_plate, String color, String brand, String room_number) {
        this._id = _id;
        this.license_plate = license_plate;
        this.color = color;
        this.brand = brand;
        this.room_number = room_number;
    }
}
