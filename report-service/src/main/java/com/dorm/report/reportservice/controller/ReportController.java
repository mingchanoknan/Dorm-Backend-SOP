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
@RequestMapping("report")
public class ReportController {
    @Autowired
    public ReportService reportService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/getall")
    public List<Report> getAllReport(){
        rabbitTemplate.convertAndSend("ReportExchange","getReport",reportService.getAllReport());
        System.out.println("get");
        return reportService.getAllReport();
    }
    @PostMapping("/add")
    public String addReport(@RequestBody Report report){
       String result = String.valueOf(rabbitTemplate.convertSendAndReceive("ReportExchange","addReport",reportService.addReport(report)));
        System.out.println(report);
        return result;
    }
    @PutMapping("/update")
    public String updateReport(@RequestBody Report report){
        rabbitTemplate.convertAndSend("ReportExchange","updateReport",reportService.updateReport(report));
        return reportService.updateReport(report);
    }

    @GetMapping("/status")
    public List<Report> getByStatus(@RequestParam boolean status){
        rabbitTemplate.convertAndSend("ReportExchange","statusReport",reportService.getByStatus(status));
        return reportService.getByStatus(status);
    }

}