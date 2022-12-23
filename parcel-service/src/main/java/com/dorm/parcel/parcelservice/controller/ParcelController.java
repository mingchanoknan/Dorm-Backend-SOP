package com.dorm.parcel.parcelservice.controller;

import com.dorm.parcel.parcelservice.pojo.Parcel;
import com.dorm.parcel.parcelservice.service.ParcelService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ParcelController {
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ParcelController(ParcelService parcelService, RabbitTemplate rabbitTemplate) {
        this.parcelService = parcelService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value ="/parcel", method = RequestMethod.GET)
    public List<Parcel> getParcel(){
        List<Parcel> parcels = (List<Parcel>) rabbitTemplate.convertSendAndReceive("ParcelExchange", "getParcel", "");
        System.out.println("get");
        return parcels;
    }

    @RequestMapping(value ="/addParcel", method = RequestMethod.POST)
    public boolean addParcel(@RequestBody Parcel parcel){
        try {
            parcelService.addParcel(parcel);
            Object result = rabbitTemplate.convertSendAndReceive("ParcelExchange", "addParcel", parcel);
            System.out.println(parcel);
            return true;
        }catch (Exception e){
            return false;
        }

//        return (String) result;
    }

    @RequestMapping(value ="/updateParcel", method = RequestMethod.POST)
    public boolean updateParcel(@RequestBody Parcel parcel){
        try {
            parcelService.updateParcel(parcel);
            Object result = rabbitTemplate.convertSendAndReceive("ParcelExchange", "updateParcel", parcel);
            System.out.println(parcel);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deleteParcel", method = RequestMethod.POST)
    public boolean deleteParcel(@RequestBody Parcel parcel){
        try {
            parcelService.deleteParcel(parcel);
            Object result = rabbitTemplate.convertSendAndReceive("ParcelExchange", "deleteParcel", parcel);
            System.out.println(parcel);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getParcelNum/{room_number}", method = RequestMethod.GET)
    public List<Parcel> getParcelNum(@PathVariable("room_number") String room_number){
        try {
            System.out.println("getParcelNum");
            List<Parcel> parcel = (List<Parcel>) rabbitTemplate.convertSendAndReceive("ParcelExchange","getByNum", room_number);
//            List<Parcel> parcel = parcelService.getRoomByNumber(room_number);
            return parcel;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/getParcelById/{id}", method = RequestMethod.GET)
    public Optional<Parcel> getParcelById(@PathVariable("id") String id){
        try {
            System.out.println("getParcelById");
            Optional<Parcel> parcel = (Optional<Parcel>) rabbitTemplate.convertSendAndReceive("ParcelExchange","getById", id);
            return parcel;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/getParcelByStatus/{status}", method = RequestMethod.GET)
    public List<Parcel> getParcelByStatus(@PathVariable("status") String status){
        try {
            System.out.println("getParcelByStatus");
            List<Parcel> parcel = (List<Parcel>) rabbitTemplate.convertSendAndReceive("ParcelExchange","getByStatus", status);
            return parcel;
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/countParcel/{status}", method = RequestMethod.GET)
    public int countParcel(@PathVariable("status") String status){
//        int count = rabbitTemplate.convertSendAndReceive("ParcelExchange","countParcel", status);
        return (int)rabbitTemplate.convertSendAndReceive("ParcelExchange","countParcel", status);
//        return parcelService.countParcel(status);
    }
}
