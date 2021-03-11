package com.example.volunteer.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity(name = "activity")
public class Activity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    @ApiModelProperty(value = "活动id")
    private long activityId;

    @Column(name = "activity_name",nullable = false)
    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @Column(name = "activity_content",nullable = false)
    @ApiModelProperty(value = "活动内容")
    private String activityContent;

    @Column(name = "activity_organizer",nullable = false)
    @ApiModelProperty(value = "活动组织者")
    private String activityOrganizer;

    @Column(name = "activity_date",nullable = false)
    @ApiModelProperty(value = "活动时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activityDate;

    @Column(name = "activity_picture")
    @ApiModelProperty(value = "活动图片")
    private String activityPicture;


    public Activity(){}

     public Activity(String activityName, String activityContent, String activityOrganizer, Date activityDate, String activityPicture) {
        this.activityName = activityName;
        this.activityDate = activityDate;
        this.activityContent = activityContent;
        this.activityOrganizer = activityOrganizer;
        this.activityPicture = activityPicture;
    }



}
