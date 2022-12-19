package com.dorm.reserve.reserveservice.core.repository;



import com.dorm.reserve.reserveservice.core.pojo.Reserve;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository  extends MongoRepository<Reserve, String> {
    @Query(value = "{room_number:'?0'}")
    public List<Reserve> findByRoomNumber(String room_number);
    @Query(value = "{room_number:'?0'}")
    public Reserve findByNum(String room_number);

    @Query(value = "{status:'?0'}")
    public Reserve findByStatusRoom(String status);

}
