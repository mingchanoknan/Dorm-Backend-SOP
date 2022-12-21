package com.dorm.news.newsservice.core.event;

import com.dorm.news.newsservice.core.pojo.News;
import com.dorm.news.newsservice.core.repository.NewsRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NewsEventHandler {
    private final NewsRepository newsRepository;

    public NewsEventHandler(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @EventHandler
    public void on(NewsCreatedEvent event){
        News news = new News();
        BeanUtils.copyProperties(event,news);
        newsRepository.insert(news);
        System.out.println("add news in mongo");
    }
    @EventHandler
    public void on(NewsUpdateEvent event){
        News news = new News();
        BeanUtils.copyProperties(event,news);
        newsRepository.save(news);
        System.out.println("update news in mongo");
    }
    @EventHandler
    public void on(NewsDeleteEvent event){
        News news = new News();
        BeanUtils.copyProperties(event,news);
        newsRepository.delete(news);
        System.out.println("delete news in mongo");
    }
}
