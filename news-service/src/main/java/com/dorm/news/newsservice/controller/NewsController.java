package com.dorm.news.newsservice.controller;


import com.dorm.news.newsservice.pojo.News;
import com.dorm.news.newsservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<News> getNews(){
        return newsService.getNews();
    }

    @RequestMapping(value ="/addNews", method = RequestMethod.POST)
    public String addNews(@RequestBody News news){
        try {
            return newsService.addNews(news);
        }catch (Exception e){
            return "fail to created";
        }
    }

    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
    public boolean updateNews(@RequestBody News news){
        try{
            newsService.updateNews(news);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public boolean deleteNews(@RequestBody News news){
        try{
            newsService.deleteNews(news);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
