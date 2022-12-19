package com.dorm.reserve.reserveservice.core.event;

import com.dorm.reserve.reserveservice.core.pojo.Reserve;
import com.dorm.reserve.reserveservice.core.repository.ReserveRepository;
import com.dorm.reserve.reserveservice.core.rest.ReserveRestModel;
import com.dorm.reserve.reserveservice.query.FindAllReservesQuery;
import com.dorm.reserve.reserveservice.query.FindByIdReserveQuery;
import com.dorm.reserve.reserveservice.query.FindByRoomNumberReserveQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReserveQueryHandler {
    private final ReserveRepository reserveRepository;
    private List<ReserveRestModel> reserveRest;
    public ReserveQueryHandler(ReserveRepository reserveRepository, List<ReserveRestModel> reserveRest) {
        this.reserveRepository = reserveRepository;
        this.reserveRest = reserveRest;
    }
    @QueryHandler
    List<ReserveRestModel> findAllReserve(FindAllReservesQuery query){
        this.reserveRest.clear();
        List<Reserve> storeReserves = reserveRepository.findAll();
        for(Reserve reserve: storeReserves ){
            ReserveRestModel reserveRestModel = new ReserveRestModel();
            BeanUtils.copyProperties(reserve,reserveRestModel);
            this.reserveRest.add(reserveRestModel);
        }
        return this.reserveRest;
    }

    @QueryHandler
    List<ReserveRestModel> findByRoomNumberReserve(FindByRoomNumberReserveQuery query){
       this.reserveRest.clear();
        List<Reserve> storeReserves = reserveRepository.findByRoomNumber(query.getRoomNumber());
        for(Reserve reserve: storeReserves ){
            ReserveRestModel reserveRestModel = new ReserveRestModel();
            BeanUtils.copyProperties(reserve,reserveRestModel);
            this.reserveRest.add(reserveRestModel);
        }
        return this.reserveRest;
    }
    @QueryHandler
    ReserveRestModel findByIdReserve(FindByIdReserveQuery query){
        this.reserveRest.clear();
        Reserve storeReserves = reserveRepository.findById(query.getId()).get();
            ReserveRestModel reserveRestModel = new ReserveRestModel();
            BeanUtils.copyProperties(storeReserves,reserveRestModel);


        return reserveRestModel;
    }

}
