package com.dorm.invoice.invoiceservice.pojo;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Invoices")
public class Invoices implements Serializable {
    @Id
    private String _id;
    private String month;
    private int year;
    private String room_number;
    private String invoice_date;
    private int common_fee;
    private int dorm_fee;
    private int electricity_fee;
    private int water_fee;
    private int expenses;
    private int fine;
    private double amount;
    private double tax;
    private double total;
    private String note;
    private String status;

    public Invoices() {
    }

    public Invoices(String _id, String month, int year, String room_number, String invoice_date, int common_fee, int dorm_fee, int electricity_fee, int water_fee, int expenses, int fine, double amount, double tax, double total, String note, String status) {
        this._id = _id;
        this.month = month;
        this.year = year;
        this.room_number = room_number;
        this.invoice_date = invoice_date;
        this.common_fee = common_fee;
        this.dorm_fee = dorm_fee;
        this.electricity_fee = electricity_fee;
        this.water_fee = water_fee;
        this.expenses = expenses;
        this.fine = fine;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
        this.note = note;
        this.status = status;
    }
}

