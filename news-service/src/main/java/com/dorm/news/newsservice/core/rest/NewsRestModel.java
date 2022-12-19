package com.dorm.news.newsservice.core.rest;

import lombok.Data;

import java.util.ArrayList;

@Data
public class NewsRestModel {
    private String _id;
    private String title;
    private String text;
    private String created_date;
    private int created_byId;
    private ArrayList<String> url;
}
