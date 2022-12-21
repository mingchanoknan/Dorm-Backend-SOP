package com.dorm.roomservicegradle.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document("Room")
public class Room implements Serializable {
    @Id
    private String _id;
    private String suggestion;
    private String information;
    private ArrayList<String> convenience;
    private Integer price;
    private  String bgColor;
    private String iconColor;
    private String typeName;
    private ArrayList<String> image;


    public Room() {
    }

    public Room(String _id, String suggestion, String information, ArrayList<String> convenience, Integer price, String bgColor, String iconColor, String typeName, ArrayList<String> image) {
        this._id = _id;
        this.suggestion = suggestion;
        this.information = information;
        this.convenience = convenience;
        this.price = price;
        this.bgColor = bgColor;
        this.iconColor = iconColor;
        this.typeName = typeName;
        this.image = image;
    }
}
