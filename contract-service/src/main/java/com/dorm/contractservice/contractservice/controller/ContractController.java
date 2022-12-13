package com.dorm.contractservice.contractservice.controller;

import com.dorm.contractservice.contractservice.pojo.Contract;
import com.dorm.contractservice.contractservice.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {
    @Autowired
    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @RequestMapping(value ="/contracts", method = RequestMethod.GET)
    public List<Contract> getRent(){
        return contractService.getContract();
    }

    @RequestMapping(value ="/addContract", method = RequestMethod.POST)
    public boolean addRent(@RequestBody Contract contract){
        try {
            contractService.addContract(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/updateContract", method = RequestMethod.POST)
    public boolean updateRent(@RequestBody Contract contract){
        try {
            contractService.updateContract(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deleteContract", method = RequestMethod.POST)
    public boolean deleteRent(@RequestBody Contract contract){
        try {
            contractService.deleteContract(contract);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/updateStatusContract/{room_number}/{status}", method = RequestMethod.PUT)
    public boolean updateStatus(@PathVariable("room_number") String room_number,
                                @PathVariable("status") String status){
        try {
            Contract contract = contractService.getRoomByNumber(room_number);
            if(contract != null) {
                contractService.updateStatus(new Contract(contract.get_id(), contract.getFirst_name(), contract.getLast_name(), contract.getAddress(), contract.getPhone(), contract.getStart_date(), contract.getEnd_date(), contract.getRoom_price(), contract.getRoom_number(),  contract.getRoom_type(), contract.getLease_date(), status));
                return true;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getContractNum/{room_number}", method = RequestMethod.GET)
    public List<Contract> getContractNum(@PathVariable("room_number") String room_number){
        try {
            List<Contract> contracts = contractService.getRoomByNumStatus(room_number);
            List<Contract> ans = contracts.stream().filter(c -> c.getStatus().equals("rent")).toList();
            return ans;
        }catch (Exception e){
            return null;
        }
    }
}
