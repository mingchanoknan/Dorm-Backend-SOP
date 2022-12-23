package com.dorm.report.reportservice.controller;

import com.dorm.report.reportservice.pojo.Report;
import com.dorm.report.reportservice.service.ReportService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    public ReportService reportService;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public ReportController(ReportService reportService, RabbitTemplate rabbitTemplate) {
        this.reportService = reportService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/getall")
    public List<Report> getAllReport(){
        List<Report> reports = (List<Report>) rabbitTemplate.convertSendAndReceive("ReportExchange","getReport","");
        System.out.println("get");
        return reports;
    }
    @PostMapping("/add")
    public String addReport(@RequestBody Report report){
        Object result = rabbitTemplate.convertSendAndReceive("ReportExchange","addReport", report);
        System.out.println(report);
        return (String) result;
    }
    @PutMapping("/update")
   public String updateReport(@RequestBody Report report){
//        rabbitTemplate.convertAndSend("ReportExchange","updateReport",reportService.updateReport(report));
Object result = rabbitTemplate.convertSendAndReceive("ReportExchange","updateReport", report);
        System.out.println(report);
        return (String) result;
//        return reportService.updateReport(report);
   }

   @GetMapping("/status")
   public List<Report> getByStatus(@RequestParam boolean status){
//        rabbitTemplate.convertAndSend("ReportExchange","statusReport",reportService.getByStatus(status));
//        return reportService.getByStatus(status);
       List<Report> reports = (List<Report>) rabbitTemplate.convertSendAndReceive("ReportExchange","statusReport",status);
       System.out.println("get");
       return reports;

   }

}