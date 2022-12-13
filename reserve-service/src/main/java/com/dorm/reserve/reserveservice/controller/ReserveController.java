package com.dorm.reserve.reserveservice.controller;




import com.dorm.reserve.reserveservice.pojo.Reserve;
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
    public List<Reserve> getInvoices(){
        return reserveService.getReserve();
    }

    @RequestMapping(value ="/addReserve", method = RequestMethod.POST)
    public boolean addReserve(@RequestBody Reserve reserve){
        try {
            reserveService.addReserve(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/updateReserve", method = RequestMethod.POST)
    public boolean updateReserve(@RequestBody Reserve reserve){
        try {
            reserveService.updateReserve(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deleteReserve", method = RequestMethod.POST)
    public boolean deleteReserve(@RequestBody Reserve reserve){
        try {
            reserveService.deleteReserve(reserve);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getReserveNum/{room_number}", method = RequestMethod.GET)
    public List<Reserve> getReserveNum(@PathVariable("room_number") String room_number){
        try {
            List<Reserve> reserves = reserveService.getRoomByNumber(room_number);
            List<Reserve> ans = reserves.stream().filter(c -> c.getStatus().equals("reserve")).toList();
            return ans;
        }catch (Exception e){
            return null;
        }
    }
    @RequestMapping(value ="/getReserveId/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReserveId(@PathVariable("id") String id){
        try {
            Optional<Reserve> reserve = reserveService.getRoomById(id);
            return ResponseEntity.ok(reserve);
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/updateStatusReserve/{reserveId}/{status}", method = RequestMethod.PUT)
    public boolean updateStatusReserve(@PathVariable("reserveId") String reserveId, @PathVariable("status") String status){
        try {
            Optional<Reserve> reserve = reserveService.getRoomById(reserveId);
            if(reserve != null) {
                reserveService.updateStatus(new Reserve(reserve.get().get_id(), reserve.get().getRoom_number(), reserve.get().getFirst_name(), reserve.get().getLast_name(), reserve.get().getMobile(), reserve.get().getReserve_date(), reserve.get().getLease_date(), status));
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

