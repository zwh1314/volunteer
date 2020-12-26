package com.example.volunteer.Entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class ActivityUserKey implements Serializable {

    @Column(name = "activity_id",nullable = false)
    @ApiModelProperty(value = "活动id")
    private long activityId;

    @Column(name = "user_id",nullable = false)
    @ApiModelProperty(value = "用户id")
    private long userId;
}
