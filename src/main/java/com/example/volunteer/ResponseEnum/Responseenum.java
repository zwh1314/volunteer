package com.example.volunteer.ResponseEnum;

import lombok.Getter;

@Getter
public enum Responseenum {

    SUCCESS(200, "成功"),

    FAIL(1, "失败"),

    ILLEGAL_PARAM(101, "参数错误"),
    USER_NAME_OR_PWD_ERROR(102, "用户名或密码错误"),
    VERIFY_MSG_CODE_INVALID(103, "短信验证码已失效，请重试"),

    SERVER_ERROR(500, "服务器内部错误")
    ;

    private int code;
    private String msg;

    Responseenum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
