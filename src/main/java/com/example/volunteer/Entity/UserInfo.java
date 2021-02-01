package com.example.volunteer.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "user_info")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户id")
    private long userId;

    @Column(name = "user_name",nullable = false)
    @ApiModelProperty(value = "用户名")
    private String userName;

    @Column(name = "priority",nullable = false)
    @ApiModelProperty(value = "优先级")
    private String priority;

    @Column(name = "mail_address",nullable = false)
    @ApiModelProperty(value = "邮箱地址")
    private String mailAddress;

    @Column(name = "head_picture",nullable = false)
    @ApiModelProperty(value = "头像")
    private String headPicture;

    @Column(name = "introduction",nullable = false)
    @ApiModelProperty(value = "个性签名")
    private String introduction;

    @Column(name = "address",nullable = false)
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "fax",nullable = false)
    @ApiModelProperty(value = "传真")
    private String fax;

    @Column(name = "tel",nullable = false)
    @ApiModelProperty(value = "手机")
    private String tel;

    @Column(name = "qq",nullable = false)
    @ApiModelProperty(value = "QQ")
    private String qq;

    @Column(name = "credits",nullable = false)
    @ApiModelProperty(value = "credits")
    private int credits;
}
