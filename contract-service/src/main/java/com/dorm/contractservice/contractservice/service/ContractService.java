package com.dorm.contractservice.contractservice.service;
import com.dorm.contractservice.contractservice.pojo.Contract;
import com.dorm.contractservice.contractservice.repository.ContractRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
@RabbitListener(queues = "getAllContractQueue")
    public List<Contract> getContract(){
        return contractRepository.findAll();
    }
@RabbitListener(queues = "addContractQueue")
    public boolean addContract(Contract contract){
        try {
            contractRepository.insert(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @RabbitListener(queues = "updateContractQueue")
    public boolean updateContract(Contract contract){
        try {
            contractRepository.save(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }
@RabbitListener(queues = "delContractQueue")
    public boolean deleteContract(Contract contract){
        try {
            System.out.println("del : "+contract);
            contractRepository.delete(contract);

            return true;
        }catch (Exception e){
            return false;
        }
    }
@RabbitListener(queues = "getRoomByNumberQueue")
    public Contract getRoomByNumber(String room_number){
        try {
            System.out.println("getByNum");
            return contractRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }
@RabbitListener(queues = "getRoomByNumStatusQueue")
    public List<Contract> getRoomByNumStatus(String room_number){
        try {
            System.out.println("getContractNum2");
            return contractRepository.findByRoomNumStatus(room_number);
        }catch (Exception e){
            return null;
        }
    }
@RabbitListener(queues = "updateStatusQueue")
    public boolean updateStatus(Contract contract){
        try {
            System.out.println("updateStatus2");

            contractRepository.save(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
