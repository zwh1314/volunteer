package com.example.volunteer.RedisService.RedisServiceImpl;

import com.example.volunteer.DTO.UserDTO;
import com.example.volunteer.Dao.UserDao;
import com.example.volunteer.Entity.User;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.RedisService.UserRedisService;
import com.example.volunteer.Response.Response;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.MsgUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class UserRedisServiceImpl implements UserRedisService {
    private Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .initialCapacity(5)
            .maximumSize(25)
            .build();

    @Autowired
    private MsgUtil msgUtil;

    @Autowired
    private UserDao userDao;

    @Override
    public Response<Boolean> signUp(User user, String verifyCode) {
        Response<Boolean> response=new Response<>();
        if (StringUtils.isBlank(cache.getIfPresent(user.getTel()))) {
            throw new VolunteerRuntimeException(ResponseEnum.VERIFY_MSG_CODE_INVALID);
        }


        boolean result = userDao.insertUser(user) > 0;
        if (result) {
            response.setSuc(true);
        } else {
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }

        return response;
    }

    @Override
    public UserDTO signIn(String userName, String password) {
        // TODO 密码加密

        UserDTO userDTO = userDao.getUserByUserNameAndPassword(userName,password);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NAME_OR_PWD_ERROR);
        }

        return userDTO;
    }

    @Override
    public Response<Boolean> updatePassword(String tel, String oldPassword, String newPassword, String verifyCode) {
        Response<Boolean> response = new Response<>();
        UserDTO userDTO = userDao.getUserByTel(tel);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NAME_OR_PWD_ERROR);
        }

        if (StringUtils.isBlank(cache.getIfPresent(tel))) {
            throw new VolunteerRuntimeException(ResponseEnum.VERIFY_MSG_CODE_INVALID);
        }

        boolean result=userDao.updatePassword(tel, newPassword) > 0;
        if (result) {
            response.setSuc(true);
        } else {
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }

        return response;
    }

    @Override
    public Response<Boolean> forgetPassword(String tel, String newPassword, String verifyCode) {
        Response<Boolean> response = new Response<>();
        if (StringUtils.isBlank(cache.getIfPresent(tel))) {
            response.setFail(ResponseEnum.VERIFY_MSG_CODE_INVALID);
            return response;
        }
        // TODO 密码错误上限次数
        // TODO 用户信息缓存
        UserDTO userDTO = userDao.getUserByTel(tel);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NOT_FOUND);
        }

        if (StringUtils.isBlank(cache.getIfPresent(tel))) {
            throw new VolunteerRuntimeException(ResponseEnum.VERIFY_MSG_CODE_INVALID);
        }

        boolean result=userDao.updatePassword(tel, newPassword) > 0;
        if (result) {
            response.setSuc(true);
        } else {
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }

        return response;
    }

    @Override
    public Response<Boolean> getVerifyMsgCode(String tel) {
        Response<Boolean> response = new Response<>();
        if (StringUtils.isNotBlank(cache.getIfPresent(tel))) {
            response.setFail(ResponseEnum.VERIFY_MSG_CODE_VALID);
            return response;
        }
        String msgCode = msgUtil.sendSignUpMsgCode(tel);
        cache.put(tel, msgCode);
        response.setSuc(true);

        return response;
    }

    @Override
    public Response<Boolean> deleteUserByUserId(long userId){
        Response<Boolean> response=new Response<>();
        UserDTO userDTO=userDao.getUserByUserId(userId);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NOT_FOUND);
        }
        boolean result = userDao.deleteByUserId(userId) > 0;
        if (result) {
            response.setSuc(true);
        } else {
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }

        return response;
    }
}
