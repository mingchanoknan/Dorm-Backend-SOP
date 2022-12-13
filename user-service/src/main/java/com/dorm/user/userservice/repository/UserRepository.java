package com.dorm.user.userservice.repository;

import com.dorm.user.userservice.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{room_number:'?0'}")
    public User findByRoomNumber(String room_number);

    @Query(value = "{username: '?0'}")
    public User findByUsername(String username);
}
