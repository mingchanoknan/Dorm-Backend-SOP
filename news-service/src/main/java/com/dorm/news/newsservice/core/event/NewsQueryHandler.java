package com.dorm.news.newsservice.core.event;

import com.dorm.news.newsservice.core.pojo.News;
import com.dorm.news.newsservice.core.repository.NewsRepository;
import com.dorm.news.newsservice.core.rest.NewsRestModel;
import com.dorm.news.newsservice.query.FindAllNewsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsQueryHandler {
    private final NewsRepository newsRepository;

    public NewsQueryHandler(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    @QueryHandler
    List<NewsRestModel> findAllNews(FindAllNewsQuery query){
        List<NewsRestModel> newsRest = new ArrayList<>();
        List<News> storeNews = newsRepository.findAll();
        for (News news: storeNews){
            NewsRestModel newsRestModel = new NewsRestModel();
            BeanUtils.copyProperties(news,newsRestModel);
            newsRest.add(newsRestModel);
        }
        return newsRest;

    }

}
