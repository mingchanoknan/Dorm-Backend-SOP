package com.dorm.report.reportservice.service;

import com.dorm.report.reportservice.pojo.Report;
import com.dorm.report.reportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    public ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReport(){
        return reportRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
    public String addReport(Report report){
        try {
            reportRepository.insert(report);
            return "Add report successfully";
        }catch (Exception e){
            return "add report failed";
        }
    }
    public String updateReport(Report report){
        try {
            reportRepository.save(report);
//            System.out.println(report);
            return "update report successfully";
        }catch (Exception e){
            return "update report failed";
        }
    }
    public List<Report> getByStatus(boolean status){
        try {
            return reportRepository.findByStatus(status);
        }catch (Exception e){
            return null;
        }
    }
}
