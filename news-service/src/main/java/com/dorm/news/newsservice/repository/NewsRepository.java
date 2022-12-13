package com.dorm.news.newsservice.repository;


import com.dorm.news.newsservice.pojo.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
}
