package com.dorm.parcel.parcelservice.service;

import com.dorm.parcel.parcelservice.pojo.Parcel;
import com.dorm.parcel.parcelservice.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelService {
    @Autowired
    private ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public List<Parcel> getParcel(){
        return parcelRepository.findAll();
    }
    public boolean addParcel(Parcel parcel){
        try {
            parcelRepository.insert(parcel);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateParcel(Parcel parcel){
        try {
            parcelRepository.save(parcel);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteParcel(Parcel parcel){
        try {
            parcelRepository.delete(parcel);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Parcel> getRoomByNumber(String room_number){
        try {
            return parcelRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateStatus(Parcel parcel){
        try {
            parcelRepository.save(parcel);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Parcel> getParcelById(String id){
        try {
            Optional<Parcel> parcel = parcelRepository.findById(id);
            return parcel;
        }catch (Exception e){
            return null;
        }
    }

    public List<Parcel> getParcelByStatus(String status){
        try {
            List<Parcel> parcel = parcelRepository.findByStatus(status);
            return parcel;
        }catch (Exception e){
            return null;
        }
    }
    public int countParcel(String status){
        return parcelRepository.countParcel(status);
    }
}
