package com.dorm.news.newsservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.ArrayList;
@Builder
@Data
public class DeleteNewsCommand {
    @TargetAggregateIdentifier
    private String aggregateId;
    private String _id;
    private String title;
    private String text;
    private String created_date;
    private int created_byId;
    private ArrayList<String> url;
}
