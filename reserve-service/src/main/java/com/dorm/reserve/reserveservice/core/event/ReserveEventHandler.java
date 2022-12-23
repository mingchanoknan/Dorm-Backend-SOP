package com.dorm.reserve.reserveservice.core.event;

import com.dorm.reserve.reserveservice.core.pojo.Reserve;
import com.dorm.reserve.reserveservice.core.repository.ReserveRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ReserveEventHandler {
    private final ReserveRepository reserveRepository;

    public ReserveEventHandler(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }
    @EventHandler
    public void on(ReserveCreateEvent event){
        System.out.println("add in DB");
        System.out.println(event);
        Reserve reserve = new Reserve();
        BeanUtils.copyProperties(event,reserve);
        reserveRepository.insert(reserve);
    }

    @EventHandler
    public void on(ReserveUpdateEvent event){
        System.out.println("update in DB");
        Reserve reserve = new Reserve();
        BeanUtils.copyProperties(event,reserve);
        reserveRepository.save(reserve);
    }
    @EventHandler
    public void on(ReserveDeleteEvent event){
        System.out.println("delete in DB");
        Reserve reserve = new Reserve();
        BeanUtils.copyProperties(event,reserve);
        reserveRepository.delete(reserve);
    }
}
