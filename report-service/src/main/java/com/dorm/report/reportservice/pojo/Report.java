package com.dorm.report.reportservice.pojo;

import com.dorm.report.reportservice.model.Comment;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document("Report")
public class Report implements Serializable {
    @Id
    private String _id;
    private String room_number;
    private String name;
    private String content;
    private String  date;
    private String topic;
    private boolean status;
    private List<Comment> comments;
    private List<String> image;

}
