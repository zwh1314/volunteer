package com.example.volunteer.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    SUCCESS(200, "成功"),

    FAIL(1, "失败"),

    ILLEGAL_PARAM(101, "参数错误"),
    USER_NAME_OR_PWD_ERROR(102, "用户名或密码错误"),
    VERIFY_MSG_CODE_INVALID(103, "短信验证码已失效，请重试"),
    USER_NOT_FOUND(104, "未找到对应的用户"),
    USERINFO_NOT_FOUND(105,"未找到对应的用户信息"),
    OBJECT_PUBLISHER_NOT_FOUND(106,"未找到该用户发布的相应信息"),
    OBJECT_RELATIVE_TEXT_NOT_FOUND(107,"未找到相关内容的信息"),
    OBJECT_IN_ONE_WEEK_NOT_FOUND(108,"未找到近一周的信息"),
    SWIPER_NEWS_NOT_FOUND(109,"未找到该新闻对应的轮播图信息"),
    SWIPER_PRIORITY_NOT_FOUND(110,"未找到该优先级对应的轮播图信息"),
    ACTIVITY_NEWS_ACTIVITY_NOT_FOUND(111,"未找到该活动对应的活动新闻信息"),
    COMMENT_RESPONSE_COMMENT_NOT_FOUND(112,"未找到该普通评论对应的评论回复信息"),
    COMMENT_RESPONSE_VIDEO_COMMENT_NOT_FOUND(113,"未找到该视频评论对应的评论回复信息"),



    PURCHASE_ORDER_NOT_FOUND(301, "未找到对应的采购计划"),

    SERVER_ERROR(500, "服务器内部错误")
    ;

    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
