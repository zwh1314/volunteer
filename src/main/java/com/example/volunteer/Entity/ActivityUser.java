package com.example.volunteer.Entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "activity_user")
@IdClass(ActivityUserKey.class)
public class ActivityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    @ ApiModelProperty(value = "活动id")
    private long activityId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id")
    private long userId;

    @Column(name = "form_status")
    @ApiModelProperty(value = "报名表提交状态")
    private String formStatus;


    @Column(name = "form_date")
    @ApiModelProperty(value = "报名表提交时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date formDate;

}