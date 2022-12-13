package com.dorm.invoice.invoiceservice.service;




import com.dorm.invoice.invoiceservice.pojo.Invoices;
import com.dorm.invoice.invoiceservice.repository.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicesService {

    @Autowired
    private InvoicesRepository invoicesRepository;


    public InvoicesService(InvoicesRepository invoicesRepository) {
        this.invoicesRepository = invoicesRepository;
    }

    public List<Invoices> getInvoices(){
        return invoicesRepository.findAll();
    }

    public boolean addInvoice(Invoices invoice){
        try {
            invoicesRepository.insert(invoice);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateInvoice(Invoices invoice){
        try {
            invoicesRepository.save(invoice);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteInvoice(Invoices invoice){
        try {
            invoicesRepository.delete(invoice);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Invoices getRoomByNumber (String room_number){
        try {
            return invoicesRepository.findByRoomNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public Optional<Invoices> getInvoiceById (String id){
        try {
            return invoicesRepository.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    public Invoices getInvoiceByNum (String room_number, String month, int year){
        try {
            return invoicesRepository.findInvoicesByNum(room_number, month, year);
        }catch (Exception e){
            return null;
        }
    }

    public List<Invoices> getRoomInvoice (String month, int year){
        try {
            return invoicesRepository.findRoom(month, year);
        }catch (Exception e){
            return null;
        }
    }

    public List<Invoices> getInvoiceByNumber (String room_number){
        try {
            return invoicesRepository.findRoomsByNumber(room_number);
        }catch (Exception e){
            return null;
        }
    }

    public Invoices getMonth (String month){
        try {
            return invoicesRepository.findByMonth(month);
        }catch (Exception e){
            return null;
        }
    }

    public Invoices getYear (String year){
        try {
            return invoicesRepository.findByYear(year);
        }catch (Exception e){
            return null;
        }
    }

    public List<Invoices> countPayInvoice(String month, int year, String status){
        try {
            return invoicesRepository.countPayInvoice(month, year, status);
        }catch (Exception e){
            return null;
        }
    }

}

