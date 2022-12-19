package com.dorm.reserve.reserveservice.controller;




import com.dorm.reserve.reserveservice.core.rest.ReserveRestModel;
import com.dorm.reserve.reserveservice.core.pojo.Reserve;
import com.dorm.reserve.reserveservice.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @RequestMapping(value ="/reserves", method = RequestMethod.GET)
    public List<ReserveRestModel> getInvoices(){
        return reserveService.getReserve();
    }

    @RequestMapping(value ="/addReserve", method = RequestMethod.POST)
    public boolean addReserve(@RequestBody ReserveRestModel reserve){
        try {
            System.out.println(reserve);
            reserveService.addReserve(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/updateReserve", method = RequestMethod.PUT)
    public boolean updateReserve(@RequestBody ReserveRestModel reserve){
        try {
            reserveService.updateReserve(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deleteReserve", method = RequestMethod.DELETE)
    public boolean deleteReserve(@RequestBody ReserveRestModel reserve){
        try {
            reserveService.deleteReserve(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getReserveNum/{room_number}", method = RequestMethod.GET)
    public List<ReserveRestModel> getReserveNum(@PathVariable("room_number") String room_number){
        try {
            List<ReserveRestModel> reserves = reserveService.getRoomByNumber(room_number);
            List<ReserveRestModel> ans = reserves.stream().filter(c -> c.getStatus().equals("reserve")).toList();
            return ans;
        }catch (Exception e){
            return null;
        }
    }
    @RequestMapping(value ="/getReserveId/{id}", method = RequestMethod.GET)
    public ReserveRestModel getReserveId(@PathVariable("id") String id){
        try {
            ReserveRestModel reserve =  reserveService.getRoomById(id);
            return reserve;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/updateStatusReserve/{reserveId}/{status}", method = RequestMethod.PUT)
    public boolean updateStatusReserve(@PathVariable("reserveId") String reserveId, @PathVariable("status") String status){
        try {
           ReserveRestModel reserve = reserveService.getRoomById(reserveId);
            if(reserve != null) {
                reserveService.updateReserve(new ReserveRestModel(reserve.get_id(), reserve.getRoom_number(), reserve.getFirst_name(), reserve.getLast_name(), reserve.getMobile(), reserve.getReserve_date(), reserve.getLease_date(), status));
                return true;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

//    @RequestMapping(value ="/getReserveStatus/{status}", method = RequestMethod.GET)
//    public ResponseEntity<?> getReserveStatus(@PathVariable("status") String status){
//        try {
//            Reserve reserve = reserveService.getRoomByNumber(status);
//            return ResponseEntity.ok(reserve);
//        }catch (Exception e){
//            return null;
//        }
//    }
}

