package com.example.volunteer.RedisService;

import com.example.volunteer.DTO.UserDTO;
import com.example.volunteer.Entity.User;
import com.example.volunteer.Response.Response;

public interface UserRedisService {
    /**
     * 注册1
     */
    Response<Boolean> signUp(User user, String verifyCode);

    /**
     * 登录
     */
    UserDTO signIn(String userName, String password);

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
