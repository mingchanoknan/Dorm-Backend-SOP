package com.dorm.news.newsservice.core.repository;


import com.dorm.news.newsservice.core.pojo.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
}
