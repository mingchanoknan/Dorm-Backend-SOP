package com.dorm.room.roomservice.repository;

import com.dorm.room.roomservice.pojo.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}
