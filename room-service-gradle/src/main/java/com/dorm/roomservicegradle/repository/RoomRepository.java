package com.dorm.roomservicegradle.repository;

import com.dorm.roomservicegradle.pojo.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}
