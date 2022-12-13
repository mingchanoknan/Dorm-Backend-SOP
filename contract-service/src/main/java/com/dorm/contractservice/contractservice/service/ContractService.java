package com.dorm.contractservice.contractservice.service;
import com.dorm.contractservice.contractservice.pojo.Contract;
import com.dorm.contractservice.contractservice.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getContract(){
        return contractRepository.findAll();
    }

    public boolean addContract(Contract contract){
        try {
            contractRepository.insert(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateContract(Contract contract){
        try {
            contractRepository.save(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteContract(Contract contract){
        try {
            contractRepository.delete(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Contract getRoomByNumber(String room_number){
        try {
            return contractRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public List<Contract> getRoomByNumStatus(String room_number){
        try {
            return contractRepository.findByRoomNumStatus(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public boolean updateStatus(Contract contract){
        try {
            contractRepository.save(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
