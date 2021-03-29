package com.example.volunteer.Controller;

import com.example.volunteer.DTO.UserDTO;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "用户Controller")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "账户密码", paramType = "query", dataType = "String"),
    })
    public Response<UserDTO> login(@RequestParam("tel") String tel, @RequestParam("password") String password, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Response<UserDTO> response = new Response<>();
        try {
            validateBaseUserInfo(tel, password);

            return userService.signIn(tel, password,servletRequest,servletResponse);
        } catch (IllegalArgumentException e) {
            logger.warn("[login Illegal Argument], tel: {}, password: {}", tel, password, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[login Runtime Exception], tel: {}, password: {}", tel, password, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[login Exception], tel: {}, password: {}", tel, password, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }



    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "query", dataType = "String"),
    })
    public Response<Boolean> updatePassword(@RequestParam("tel") String tel,
                                            @RequestParam("newPassword") String newPassword) {
        Response<Boolean> response = new Response<>();
        try {
            validateUserTelAndPassword(tel,newPassword);
            return userService.updatePassword(tel, newPassword);
        } catch (IllegalArgumentException e) {
            logger.warn("[updatePassword Illegal Argument], tel: {}, newPassword: {}", tel, newPassword, e);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updatePassword Runtime Exception], tel: {}, newPassword: {}", tel, newPassword, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[updatePassword Exception], tel: {}, newPassword: {}", tel, newPassword, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @GetMapping("/verifyVerification")
    @ApiOperation("验证验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "verifyCode", value = "验证码", paramType = "query", dataType = "String")

    })
    public Response<Boolean> verifyVerification(@RequestParam("tel")String tel,
                                                @RequestParam("verifyCode")String verifyCode){

        return userService.verifyVerification(tel,verifyCode);
    }



//    @PostMapping("/forgetPassword")
//    @ApiOperation("忘记密码")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "tel", value = "手机号", paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "verifyCode", value = "短信验证码", paramType = "query", dataType = "String"),
//    })
//    public Response<Boolean> forgetPassword(@RequestParam("tel") String tel,
//                                   @RequestParam("newPassword") String newPassword,
//                                   @RequestParam("verifyCode") String verifyCode) {
//        Response<Boolean> response = new Response<>();
//
//        try {
//            validateUserPasswordAndMsgCode(tel, newPassword, verifyCode);
//
//            return userService.forgetPassword(tel, newPassword, verifyCode);
//        } catch (IllegalArgumentException e) {
//            logger.warn("[forgetPassword Illegal Argument], tel: {}, newPassword: {}", tel, newPassword, e);
//            response.setFail(ResponseEnum.ILLEGAL_PARAM);
//            return response;
//        } catch (VolunteerRuntimeException e) {
//            logger.error("[forgetPassword Runtime Exception], tel: {}, newPassword: {}", tel, newPassword, e);
//            response.setFail(e.getExceptionCode(), e.getMessage());
//            return response;
//        } catch (Exception e) {
//            logger.error("[forgetPassword Exception], tel: {}, newPassword: {}", tel, newPassword, e);
//            response.setFail(ResponseEnum.SERVER_ERROR);
//            return response;
//        }
//    }

    @GetMapping("/getVerifyCode")
    @ApiOperation("获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tel", value = "手机号", paramType = "query", dataType = "String"),
    })
    public Response<Boolean> getVerifyCode(@RequestParam("tel") String tel) {
        Response<Boolean> response = new Response<>();

        try {
            validateBaseTel(tel);

            return userService.getVerifyMsgCode(tel);
        } catch (IllegalArgumentException e) {
            logger.warn("[getVerifyCode Illegal Argument], tel: {}", tel, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVerifyCode Runtime Exception], tel: {}", tel, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[getVerifyCode Exception], tel: {}", tel, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @GetMapping("/getMailVerifyCode")
    @ApiOperation("获取邮件验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mail", value = "邮箱地址", paramType = "query", dataType = "String"),
    })
    public Response<Boolean> getMailVerifyCode(@RequestParam("mail") String mail) {
        Response<Boolean> response = new Response<>();

        try {
            validateBaseMail(mail);

            return userService.getMailVerifyMsgCode(mail);
        } catch (IllegalArgumentException e) {
            logger.warn("[getMailVerifyCode Illegal Argument], mail: {}", mail, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getMailVerifyCode Runtime Exception], mail: {}", mail, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[getMailVerifyCode Exception], mail: {}", mail, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    private void validateBaseUserInfo(String tel, String password) {
        validateUserTel(tel);
        validateUserPassword(password);
    }

    private void validateBaseUserInfoByEmail(String mail, String password) {
        validateUserMail(mail);
        validateUserPassword(password);
    }

    private void validateBaseTel(String tel) {
        validateUserTel(tel);
    }

    private void validateBaseMail(String mail) {
        validateUserMail(mail);
    }

    private void validateUserTelAndPassword(String tel, String newPassword) {
        validateUserTel(tel);
        validateUserPassword(newPassword);
    }
    private void validateUserPasswordAndMsgCodeByEmail(String mail, String newPassword, String verifyCode) {
        validateUserMail(mail);
        validateUserPassword(newPassword);
        validateVerifyMsgCode(verifyCode);
    }

    private void validateUserPasswordAndMsgCode(String tel, String oldPassword, String newPassword, String verifyCode) {
        validateUserTel(tel);
        validateUserPassword(oldPassword);
        validateUserPassword(newPassword);
        validateVerifyMsgCode(verifyCode);
    }

    private void validateUserInfoAndVerifyCode(String tel, String password, String verifyCode) {
        validateBaseUserInfo(tel, password);
        validateVerifyMsgCode(verifyCode);
    }
}
