package com.example.volunteer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param: none
 * @description: 返回给前端的用户信息实体类
 * @author: KingJ
 **/
@ApiModel("用户登录注册模型")
@Data
public class UserDTO {

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "优先级")
    private String priority;

    @ApiModelProperty(value = "手机号")
    private String tel;

    @ApiModelProperty(value = "邮箱地址")
    private  String mailAddress;
}
