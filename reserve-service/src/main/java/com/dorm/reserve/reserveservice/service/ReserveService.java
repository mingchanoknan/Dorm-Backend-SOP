package com.dorm.reserve.reserveservice.service;


import com.dorm.reserve.reserveservice.command.CreateReserveCommand;
import com.dorm.reserve.reserveservice.command.DeleteReserveCommand;
import com.dorm.reserve.reserveservice.core.rest.ReserveRestModel;
import com.dorm.reserve.reserveservice.command.UpdateReserveCommand;
import com.dorm.reserve.reserveservice.core.pojo.Reserve;
import com.dorm.reserve.reserveservice.core.repository.ReserveRepository;
import com.dorm.reserve.reserveservice.query.FindAllReservesQuery;
import com.dorm.reserve.reserveservice.query.FindByIdReserveQuery;
import com.dorm.reserve.reserveservice.query.FindByRoomNumberReserveQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReserveService {


    private ReserveRepository reserveRepository;
    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;
    @Autowired
    public ReserveService(ReserveRepository reserveRepository, CommandGateway commandGateway, QueryGateway queryGateway) {
        this.reserveRepository = reserveRepository;
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public List<ReserveRestModel> getReserve(){
        FindAllReservesQuery findAllReservesQuery = new FindAllReservesQuery();
        List<ReserveRestModel> reserves = queryGateway.query(findAllReservesQuery, ResponseTypes.multipleInstancesOf(ReserveRestModel.class)).join();
        return reserves;
    }

    public boolean addReserve(ReserveRestModel reserve){
        System.out.println("add reserve");
        CreateReserveCommand command = CreateReserveCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                ._id(reserve.get_id())
                .reserve_date(reserve.getReserve_date())
                .first_name(reserve.getFirst_name())
                .mobile(reserve.getMobile())
                .last_name(reserve.getLast_name())
                .lease_date(reserve.getLease_date())
                .room_number(reserve.getRoom_number())
                .status(reserve.getStatus())
                .build();
        try {
            commandGateway.send(command);
//            reserveRepository.insert(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateReserve(ReserveRestModel reserve){
       UpdateReserveCommand command = UpdateReserveCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                ._id(reserve.get_id())
                .reserve_date(reserve.getReserve_date())
                .first_name(reserve.getFirst_name())
                .mobile(reserve.getMobile())
                .last_name(reserve.getLast_name())
                .lease_date(reserve.getLease_date())
                .room_number(reserve.getRoom_number())
                .status(reserve.getStatus())
                .build();
        try {
            commandGateway.send(command);
//            reserveRepository.save(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteReserve(ReserveRestModel reserve){
        DeleteReserveCommand command = DeleteReserveCommand.builder()
                .aggregateId(UUID.randomUUID().toString())
                ._id(reserve.get_id())
                .reserve_date(reserve.getReserve_date())
                .first_name(reserve.getFirst_name())
                .mobile(reserve.getMobile())
                .last_name(reserve.getLast_name())
                .lease_date(reserve.getLease_date())
                .room_number(reserve.getRoom_number())
                .status(reserve.getStatus())
                .build();
        try {
//            reserveRepository.delete(reserve);
            commandGateway.send(command);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<ReserveRestModel> getRoomByNumber (String room_number) {
        try {
            FindByRoomNumberReserveQuery findByRoomNumberReserveQuery = new FindByRoomNumberReserveQuery();
            findByRoomNumberReserveQuery.setRoomNumber(room_number);
            List<ReserveRestModel> reserves = queryGateway.query(findByRoomNumberReserveQuery, ResponseTypes.multipleInstancesOf(ReserveRestModel.class)).join();
            return reserves;
//            return reserveRepository.findByRoomNumber(room_number);
        } catch (Exception e) {
            return null;
        }
    }

    public Reserve getRoomByNum (String room_number) {
        try {
            return reserveRepository.findByNum(room_number);
        } catch (Exception e) {
            return null;
        }
    }

    public ReserveRestModel getRoomById (String id) {
        try {
            FindByIdReserveQuery findByIdReserveQuery = new FindByIdReserveQuery();
            findByIdReserveQuery.setId(id);
            List<ReserveRestModel> reserves = queryGateway.query(findByIdReserveQuery, ResponseTypes.multipleInstancesOf(ReserveRestModel.class)).join();
            return reserves.get(0);
//            return reserveRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean updateStatus(Reserve reserve){
        try {
            reserveRepository.save(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Reserve getRoomByStatus (String status) {
        try {
            return reserveRepository.findByStatusRoom(status);
        } catch (Exception e) {
            return null;
        }
    }
}

