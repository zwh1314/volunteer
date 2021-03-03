package com.example.volunteer.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id")
    private long userId;

    @Column(name = "user_name",nullable = false)
    @ApiModelProperty(value = "用户名")
    private String userName;

    @Column(name = "password",nullable = false)
    @ApiModelProperty(value = "密码")
    private String password;

    @Column(name = "priority",nullable = false)
    @ApiModelProperty(value = "优先级")
    private String priority;

    @Column(name = "tel",nullable = false)
    @ApiModelProperty(value = "手机")
    private String tel;

    @Column(name = "mail_address",nullable = false)
    @ApiModelProperty(value = "邮箱地址")
    private String mailAddress;
}
