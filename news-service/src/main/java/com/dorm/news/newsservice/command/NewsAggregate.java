package com.dorm.news.newsservice.command;

import com.dorm.news.newsservice.core.event.NewsCreatedEvent;
import com.dorm.news.newsservice.core.event.NewsUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.UUID;

@Aggregate
public class NewsAggregate {
    @AggregateIdentifier
    private String aggregateId;

    private String _id;
    private String title;
    private String text;
    private String created_date;
    private int created_byId;
    private ArrayList<String> url;

    public NewsAggregate() {
    }
    @CommandHandler
    public NewsAggregate(CreateNewsCommand createNewsCommand) {
        System.out.println("create news command handler");
        NewsCreatedEvent newsCreatedEvent = new NewsCreatedEvent();
        BeanUtils.copyProperties(createNewsCommand, newsCreatedEvent);
        AggregateLifecycle.apply(newsCreatedEvent);
    }

    @CommandHandler
    public NewsAggregate(UpdateNewsCommand updateNewsCommand) {
        System.out.println("update news command handler");
        NewsUpdateEvent newsUpdateEvent = new NewsUpdateEvent();
        BeanUtils.copyProperties(updateNewsCommand, newsUpdateEvent);
        AggregateLifecycle.apply(newsUpdateEvent);
    }

    @EventSourcingHandler
    public void on(NewsCreatedEvent newsCreatedEvent){
        System.out.println("add news event sourcing handler");
        this.aggregateId = UUID.randomUUID().toString();
        this._id = newsCreatedEvent.get_id();
        this.title = newsCreatedEvent.getTitle();
        this.text = newsCreatedEvent.getText();
        this.created_byId = newsCreatedEvent.getCreated_byId();
        this.created_date = newsCreatedEvent.getCreated_date();
        this.url = newsCreatedEvent.getUrl();
    }

    @EventSourcingHandler
    public void on(NewsUpdateEvent newsUpdateEvent) {
        System.out.println("update news event sourcing handler");
        this.aggregateId = UUID.randomUUID().toString();
        this._id = newsUpdateEvent.get_id();
        this.title = newsUpdateEvent.getTitle();
        this.text = newsUpdateEvent.getText();
        this.created_byId = newsUpdateEvent.getCreated_byId();
        this.created_date = newsUpdateEvent.getCreated_date();
        this.url = newsUpdateEvent.getUrl();
    }
}
