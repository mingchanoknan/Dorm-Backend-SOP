package com.dorm.news.newsservice.service;

import com.dorm.news.newsservice.pojo.News;
import com.dorm.news.newsservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getNews(){
        return newsRepository.findAll();
    }

    public String addNews(News news){
        try {
            newsRepository.insert(news);
            return "created news successfully";
        }catch (Exception e){
            return "fail";
        }
    }

    public boolean updateNews(News news){
        try{
            newsRepository.save(news);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteNews(News news){
        try {
            newsRepository.delete(news);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
