package com.dorm.report.reportservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {
    private String name;
    private String date;
    private String comment;

}
