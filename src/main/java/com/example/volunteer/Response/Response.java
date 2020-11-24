package com.example.volunteer.Response;

import com.example.volunteer.ResponseEnum.Responseenum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @param: none
 * @description:
 * @author: KingJ
 **/
@Api("请求通用返回数据模型")
@Data
@ToString
public class Response<T> {

    @ApiModelProperty("请求是否成功 仅代表请求是否成功 无业务相关含义")
    private boolean success;

    @ApiModelProperty("返回状态码")
    private int code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回数据格式")
    private T result;

    public void setSuc(String msg) {
        setSuccess(true);
        setCode(Responseenum.SUCCESS.getCode());
        setMsg(msg);
    }

    public void setSuc(T data) {
        setSuccess(true);
        setCode(Responseenum.SUCCESS.getCode());
        setMsg(Responseenum.SUCCESS.getMsg());
        setResult(data);
    }

    public void setFail(String msg) {
        setSuccess(false);
        setCode(Responseenum.FAIL.getCode());
        setMsg(msg);
        setResult(null);
    }

    public void setFail(int code, String msg) {
        setSuccess(false);
        setCode(code);
        setMsg(msg);
        setResult(null);
    }

    public void setFail(Responseenum responseStatus) {
        setSuccess(false);
        setCode(responseStatus.getCode());
        setMsg(responseStatus.getMsg());
        setResult(null);
    }
}

