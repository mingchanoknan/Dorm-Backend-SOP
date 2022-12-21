package com.dorm.roomservicegradle.controller;


import com.dorm.roomservicegradle.pojo.Room;
import com.dorm.roomservicegradle.service.RoomService;
import com.proto.room.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {


    private RoomService roomService;
    private ManagedChannel channel;
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
        this.channel = ManagedChannelBuilder.forAddress("localhost", 50058).usePlaintext().build();
    }

    @GetMapping("/getall")
    public List<Room> getAllRoom() {
        List<Room> rooms = roomService.getAllRoom();
        return rooms;

    }

    @GetMapping("/getbyid")
    public Room getById(@RequestParam String id) {
        Room room = roomService.getById(id);
        return room;
    }
    @PostMapping(value ="/add")
    public String addNewRoom(@RequestBody Room room){
        RoomData newRoom = RoomData.newBuilder()
                .setBgColor(room.getBgColor())
                .addAllConvenience(room.getConvenience())
                .addAllImage(room.getImage())
                .setInformation(room.getInformation())
                .setIconColor(room.getIconColor())
                .setSuggestion(room.getSuggestion())
                .setPrice(room.getPrice())
                .setTypeName(room.getTypeName())
                .build();
        RoomServiceGrpc.RoomServiceBlockingStub client = RoomServiceGrpc.newBlockingStub(channel);
        AddRoomRequest request = AddRoomRequest.newBuilder()
                .setNewRoom(newRoom).build();
        AddRoomResponse response = client.addRoom(request);
        return response.getResult();
//        return roomService.addNewRoomType(room);
    }
    @PutMapping("/update")
    public String updateRoom(@RequestBody Room room){
        RoomData updateRoom = RoomData.newBuilder()
                .setId(room.get_id())
                .setBgColor(room.getBgColor())
                .addAllConvenience(room.getConvenience())
                .addAllImage(room.getImage())
                .setInformation(room.getInformation())
                .setIconColor(room.getIconColor())
                .setSuggestion(room.getSuggestion())
                .setPrice(room.getPrice())
                .setTypeName(room.getTypeName())
                .build();
        RoomServiceGrpc.RoomServiceBlockingStub client = RoomServiceGrpc.newBlockingStub(channel);
        UpdateRoomRequest request = UpdateRoomRequest.newBuilder()
                .setUpdateRoom(updateRoom).build();
        UpdateRoomResponse response = client.updateRoom(request);
        return response.getResult();
//        return roomService.updateRoomType(room);
    }
    @DeleteMapping("/delete")
    public String deleteRoom(@RequestParam String id){
        RoomServiceGrpc.RoomServiceBlockingStub client = RoomServiceGrpc.newBlockingStub(channel);
        DeleteRoomRequest request =DeleteRoomRequest.newBuilder().setRoomId(id).build();
        DeleteRoomResponse response = client.deleteRoom(request);
        return response.getResult();
//        return  roomService.deleteRoomType(id);
    }
}
