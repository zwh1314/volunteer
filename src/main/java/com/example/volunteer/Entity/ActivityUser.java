package com.example.volunteer.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "activity_user")
@IdClass(ActivityUserKey.class)
public class ActivityUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @ApiModelProperty(value = "记录id")
    private long id;

    @Id
    @Column(name = "activity_id",nullable = false)
    @ ApiModelProperty(value = "活动id")
    private long activityId;

    @Id
    @Column(name = "user_id",nullable = false)
    @ApiModelProperty(value = "用户id")
    private long userId;

    @Column(name = "form_status")
    @ApiModelProperty(value = "报名表提交状态")
    private String formStatus;


    @Column(name = "form_date")
    @ApiModelProperty(value = "报名表提交时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date formDate;

}