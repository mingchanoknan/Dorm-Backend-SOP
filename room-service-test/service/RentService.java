package com.dorm.roomservicegradle.service;



import com.dorm.roomservicegradle.pojo.Rent;
import com.dorm.roomservicegradle.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<Rent> getRent(){
        return rentRepository.findAll();
    }
    public boolean addRent(Rent rent){
        try {
            rentRepository.insert(rent);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateRent(Rent rent){
        try {
            rentRepository.save(rent);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteRent(Rent rent){
        try {
            rentRepository.delete(rent);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Rent getRoomByNumber(String room_number){
        try {
            return rentRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateStatus(Rent rent){
        try {
            rentRepository.save(rent);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public int countRoom(String status) {
        return rentRepository.countRoom(status);
    }

    public int countRentByType (String type, String status) throws Exception {
        try{
            System.out.println(rentRepository.countRoomType(type,status).size());
            int count = rentRepository.countRoomType(type,status).size();
            return count;
        }catch (Exception e){
            throw new Exception(e);
        }


    }

    public List<Rent> getByType(String type){
        try {
            return rentRepository.findbyRoomType(type);
        }catch (Exception e){
            return null;
        }

    }
}

