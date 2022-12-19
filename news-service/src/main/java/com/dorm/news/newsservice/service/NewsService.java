package com.dorm.news.newsservice.service;

import com.dorm.news.newsservice.command.CreateNewsCommand;
import com.dorm.news.newsservice.command.DeleteNewsCommand;
import com.dorm.news.newsservice.command.UpdateNewsCommand;
import com.dorm.news.newsservice.core.pojo.News;
import com.dorm.news.newsservice.core.repository.NewsRepository;
import com.dorm.news.newsservice.core.rest.NewsRestModel;
import com.dorm.news.newsservice.query.FindAllNewsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<NewsRestModel> getNews(){
        FindAllNewsQuery findAllNewsQuery = new FindAllNewsQuery();
        List<NewsRestModel> allNews = queryGateway.query(findAllNewsQuery, ResponseTypes.multipleInstancesOf(NewsRestModel.class)).join();
        return allNews;
//        return newsRepository.findAll();
    }

    public String addNews(NewsRestModel news){
        try {
//            newsRepository.insert(news);
            CreateNewsCommand command = CreateNewsCommand.builder()
                    .aggregateId(UUID.randomUUID().toString())
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

    public boolean updateNews(NewsRestModel news){
        try{

//            newsRepository.save(news);
           UpdateNewsCommand command = UpdateNewsCommand.builder()
                   .aggregateId(UUID.randomUUID().toString())
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

    public boolean deleteNews(NewsRestModel news){
        try {
//            newsRepository.delete(news);
            DeleteNewsCommand command = DeleteNewsCommand.builder()
                    .aggregateId(UUID.randomUUID().toString())
                    ._id(news.get_id())
                    .title(news.getTitle())
                    .text(news.getText())
                    .created_date(news.getCreated_date())
                    .created_byId(news.getCreated_byId())
                    .url(news.getUrl())
                    .build();
            commandGateway.send(command);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
