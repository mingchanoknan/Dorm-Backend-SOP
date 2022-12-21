package com.example.meter2service.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Meter")
public class Meter implements Serializable {
    @Id
    private String _Id;
    private String room_number;
    private String utilities_type;
    private String monthAndYear;
    private double consumption;
    private double sum;
    private int used_unit;

    public Meter() {
    }


    public Meter(String _Id, String room_number, String utilities_type, String monthAndYear, double consumption, double sum, int used_unit) {
        this._Id = _Id;
        this.room_number = room_number;
        this.utilities_type = utilities_type;
        this.monthAndYear = monthAndYear;
        this.consumption = consumption;
        this.sum = sum;
        this.used_unit = used_unit;
    }
}


