package com.dorm.roomService.service;

import com.dorm.roomService.pojo.Rent;
import com.dorm.roomService.pojo.Room;
import com.dorm.roomService.repository.RoomRepository;
import com.dorm.roomService.service.RentService;
import com.proto.room.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class RoomServiceImpl extends RoomServiceGrpc.RoomServiceImplBase {
    private RoomRepository roomRepository;
    private RentService rentService;
    private ArrayList<String> convenienceList;
    private ArrayList<String> imageList;
    private String res;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RentService rentService) {
        this.roomRepository = roomRepository;
        this.rentService = rentService;
        this.convenienceList = new ArrayList<>();
        this.imageList = new ArrayList<>();
    }

    @Override
    public void addRoom(AddRoomRequest request, StreamObserver<AddRoomResponse> responseObserver) {
        for (String conv: request.getNewRoom().getConvenienceList()){
            this.convenienceList.add(conv);
        }
        for (String img: request.getNewRoom().getImageList()){
            this.imageList.add(img);
        }
        Room newRoom = new Room(null,
                request.getNewRoom().getSuggestion(),request.getNewRoom().getInformation(),
                this.convenienceList,request.getNewRoom().getPrice(),
                request.getNewRoom().getBgColor(),request.getNewRoom().getIconColor(),
                request.getNewRoom().getTypeName(),imageList);
        try {
            roomRepository.insert(newRoom);
            this.res = "Add new room type successfully";
        }catch (Exception e){
            System.out.println(e.getMessage());
            this.res ="Add new room type failed";
        }
        this.convenienceList.clear();
        this.imageList.clear();
        AddRoomResponse response = AddRoomResponse.newBuilder().setResult(res).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateRoom(UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
        for (String conv: request.getUpdateRoom().getConvenienceList()){
            convenienceList.add(conv);
        }
        for (String img: request.getUpdateRoom().getImageList()){
            imageList.add(img);
        }
        Room room = new Room(request.getUpdateRoom().getId(),
                request.getUpdateRoom().getSuggestion(),request.getUpdateRoom().getInformation(),
                this.convenienceList,request.getUpdateRoom().getPrice(),
                request.getUpdateRoom().getBgColor(),request.getUpdateRoom().getIconColor(),
                request.getUpdateRoom().getTypeName(),imageList);
        this.roomRepository.save(room);
        try {
            Room thisRoom = roomRepository.findById(room.get_id()).get();
            List<Rent> rents= rentService.getByType(thisRoom.getTypeName());
            for (Rent item : rents){
                item.setRoom_type(room.getTypeName());
                item.setRoom_price(room.getPrice());
                rentService.updateRent(item);
            }
            roomRepository.save(room);
            this.res ="update room type successfully";
        }catch (Exception e){
            this.res = "update room type failed";
        }
        this.convenienceList.clear();
        this.imageList.clear();
        UpdateRoomResponse response = UpdateRoomResponse.newBuilder().setResult(res).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteRoom(DeleteRoomRequest request, StreamObserver<DeleteRoomResponse> responseObserver) {
        try {
            roomRepository.deleteById(request.getRoomId());
            this.res = "delete room type successfully";
        }catch (Exception e){
            this.res = "delete room type failed";
        }
        DeleteRoomResponse response = DeleteRoomResponse.newBuilder().setResult(res).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
