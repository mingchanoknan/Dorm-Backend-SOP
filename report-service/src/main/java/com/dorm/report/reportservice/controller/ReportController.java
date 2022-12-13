package com.dorm.report.reportservice.controller;

import com.dorm.report.reportservice.pojo.Report;
import com.dorm.report.reportservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    public ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/getall")
    public List<Report> getAllReport(){
        return reportService.getAllReport();
    }
    @PostMapping("/add")
    public String addReport(@RequestBody Report report){
        System.out.println(report);
        return reportService.addReport(report);
    }
    @PutMapping("/update")
    public String updateReport(@RequestBody Report report){
        return reportService.updateReport(report);
    }

    @GetMapping("/status")
    public List<Report> getByStatus(@RequestParam boolean status){
        return reportService.getByStatus(status);
    }

}
