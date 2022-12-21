package com.dorm.news.newsservice.core.event;

import lombok.Data;

import java.util.ArrayList;

@Data
public class NewsCreatedEvent {
    private String aggregateId;
    private String _id;
    private String title;
    private String text;
    private String created_date;
    private int created_byId;
    private ArrayList<String> url;
}
