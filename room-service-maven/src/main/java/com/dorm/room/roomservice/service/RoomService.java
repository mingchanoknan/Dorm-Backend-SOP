package com.dorm.room.roomservice.service;

import com.dorm.room.roomservice.pojo.Rent;
import com.dorm.room.roomservice.pojo.Room;
import com.dorm.room.roomservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RentService rentService;

    public RoomService(RoomRepository roomRepository,RentService rentService) {
        this.roomRepository = roomRepository;
        this.rentService = rentService;
    }

    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
    public Room getById(String id){
        Optional<Room> roomOptinal = roomRepository.findById(id);
        if (roomOptinal.isEmpty()) {
            return null;
        }
        else {
            return roomOptinal.get();
        }
    }
    public String addNewRoomType(Room room){
        try {
            roomRepository.insert(room);
            return "Add new room type successfully";
        }catch (Exception e){
            return "Add new room type failed";
        }
    }
    public String updateRoomType(Room room){
        try {
            Room thisRoom = getById(room.get_id());
            List<Rent> rents= rentService.getByType(thisRoom.getTypeName());
//            System.out.println(room);
            for (Rent item : rents){
                item.setRoom_type(room.getTypeName());
                item.setRoom_price(room.getPrice());
//                System.out.println(item);
                rentService.updateRent(item);
            }
            roomRepository.save(room);
            return "update room type successfully";
        }catch (Exception e){
            return "update room type failed";
        }
    }
    public String deleteRoomType(String id){
        try {
            roomRepository.deleteById(id);
            return "delete room type successfully";
        }catch (Exception e){
            return "delete room type failed";
        }
    }

}
