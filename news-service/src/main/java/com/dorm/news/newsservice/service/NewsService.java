package com.dorm.news.newsservice.service;

import com.dorm.news.newsservice.command.CreateNewsCommand;
import com.dorm.news.newsservice.command.UpdateNewsCommand;
import com.dorm.news.newsservice.core.pojo.News;
import com.dorm.news.newsservice.core.repository.NewsRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    @Autowired
    public NewsService(NewsRepository newsRepository, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.newsRepository = newsRepository;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public List<News> getNews(){
        return newsRepository.findAll();
    }

    public String addNews(News news){
        try {
//            newsRepository.insert(news);
            Object id = new ObjectId();
            news.set_id(id.toString());
            CreateNewsCommand command = CreateNewsCommand.builder()
                    ._id(news.get_id())
                    .title(news.getTitle())
                    .text(news.getText())
                    .created_date(news.getCreated_date())
                    .created_byId(news.getCreated_byId())
                    .url(news.getUrl())
                    .build();
            commandGateway.send(command);
            return "created news successfully";
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }

    public boolean updateNews(News news){
        try{
//            newsRepository.save(news);
           UpdateNewsCommand command = UpdateNewsCommand.builder()
                    ._id(news.get_id())
                    .title(news.getTitle())
                    .text(news.getText())
                    .created_date(news.getCreated_date())
                    .created_byId(news.getCreated_byId())
                    .url(news.getUrl())
                    .build();
            commandGateway.send(command);
            System.out.println("send command update news");
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
