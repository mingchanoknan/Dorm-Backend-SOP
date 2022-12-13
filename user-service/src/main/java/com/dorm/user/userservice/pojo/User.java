package com.dorm.user.userservice.pojo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;

@Data
@Document("User")
public class User implements Serializable {
    @Id
    private String _id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String tel_no1;
    private String tel_no2;
    private String address;
    private String sex;
    private String birthdate;
    private String age;
    private String room_number;
    private String role;

    public User() {
    }

    public User(String _id, String username, String password, String first_name, String last_name, String email, String tel_no1, String tel_no2, String address, String sex, String birthdate, String age, String room_number, String role) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.tel_no1 = tel_no1;
        this.tel_no2 = tel_no2;
        this.address = address;
        this.sex = sex;
        this.birthdate = birthdate;
        this.age = age;
        this.room_number = room_number;
        this.role = role;
    }
}

