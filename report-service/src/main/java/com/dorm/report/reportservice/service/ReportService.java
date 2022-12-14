package com.dorm.report.reportservice.service;

import com.dorm.report.reportservice.pojo.Report;
import com.dorm.report.reportservice.repository.ReportRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
  @RabbitListener(queues="getAllReportQueue")
    public List<Report> getAllReport(){
        return reportRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
   @RabbitListener(queues = "addReportQueue")
    public String addReports(Report report){
        try {
            reportRepository.insert(report);
            System.out.println("Finish");
            return "Add report successfully";
        }catch (Exception e){
            return "add report failed";
        }
    }
   @RabbitListener(queues = "updateReportQueue")
   public String updateReport(Report report){
        try {
            reportRepository.save(report);
           System.out.println(report);
            return "update report successfully";
        }catch (Exception e){
            return "update report failed";
        }
    }
    @RabbitListener(queues = "statusReportQueue")
    public List<Report> getByStatus(boolean status){
        try {
            return reportRepository.findByStatus(status);
        }catch (Exception e){
            return null;
        }
    }
}