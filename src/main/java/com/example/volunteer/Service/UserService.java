package com.example.volunteer.Service;


import com.example.volunteer.DTO.UserDTO;
import com.example.volunteer.Response.Response;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface UserService {

    /**
     * 注册1
     */
    Response<Boolean> signUp(String tel, String userName, String password, String verifyCode);

    /**
     * 登录
     */
    Response<UserDTO> signIn(String tel, String password, HttpServletRequest servletRequest, HttpServletResponse servletResponse);

    /**
     * 更新密码
     */
    Response<Boolean> updatePassword(String tel, String oldPassword, String newPassword, String verifyCode);

    /**
     * 忘记密码
     */
    Response<Boolean> forgetPassword(String tel, String newPassword, String verifyCode);

    /**
     * 验证登录短信验证码
     */
    Response<Boolean> getVerifyMsgCode(String tel);

    /**
     * 删除用户信息
     */
    Response<Boolean> deleteUserByUserId(long userId);
}
