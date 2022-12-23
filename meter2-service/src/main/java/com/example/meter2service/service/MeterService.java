package com.example.meter2service.service;

import com.example.meter2service.pojo.Meter;
import com.example.meter2service.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {
    @Autowired
    private MeterRepository meterRepository;

    public MeterService() {
    }

    public MeterService(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    public List<Meter> getAllMeter() {
        return meterRepository.findAll();
    }

    public String addMeter(Meter meter) {
        try {
            Meter check = meterRepository.findMeterInvoice(meter.getRoom_number(), meter.getUtilities_type(), meter.getMonthAndYear());
            System.out.println(meter);
            System.out.println(check);
            if (check != null) {
                return "This room number has already existed";
            }
            meterRepository.insert(meter);
            return "Add meter successfully";
        } catch (Exception e) {
            System.out.println("error");
            return "Can't add meter";
        }
    }

    public List<Meter> getByType(String type) {
        return meterRepository.findByName(type);
    }

    public List<Meter> getByMonthAndYear(String monthYear, String type) {
        return meterRepository.findByMonthAndYear(monthYear, type);
    }

    public Meter getMeterOfInvoice(String room_number, String type, String monthYear) {
        try {
            return meterRepository.findMeterInvoice(room_number, type, monthYear);
        } catch (Exception e) {
            return null;
        }
    }
}

