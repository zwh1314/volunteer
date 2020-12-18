package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.MsgUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.example.volunteer.DTO.UserDTO;
import com.example.volunteer.Entity.User;
import com.example.volunteer.Dao.UserDao;
import com.example.volunteer.Service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServicepImpl implements UserService {

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
    public boolean signUp(User user, String verifyCode) {
        if (StringUtils.isBlank(cache.getIfPresent(user.getTel()))) {
            throw new VolunteerRuntimeException(ResponseEnum.VERIFY_MSG_CODE_INVALID);
        }


        return userDao.insertUser(user) > 0;
    }

    @Override
    public UserDTO signIn(String userName,String password) {
        // TODO 密码加密

        UserDTO userDTO = userDao.getUserByUserNameAndPassword(userName,password);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NAME_OR_PWD_ERROR);
        }

        return userDTO;
    }

    @Override
    public boolean updatePassword(String tel, String oldPassword, String newPassword, String verifyCode) {
        UserDTO userDTO = userDao.getUserByTel(tel);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NAME_OR_PWD_ERROR);
        }

        if (StringUtils.isBlank(cache.getIfPresent(tel))) {
            throw new VolunteerRuntimeException(ResponseEnum.VERIFY_MSG_CODE_INVALID);
        }

        return userDao.updatePassword(tel, newPassword) > 0;
    }

    @Override
    public boolean forgetPassword(String tel, String newPassword, String verifyCode) {
        // TODO 密码错误上限次数
        // TODO 用户信息缓存
        UserDTO userDTO = userDao.getUserByTel(tel);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NOT_FOUND);
        }

        if (StringUtils.isBlank(cache.getIfPresent(tel))) {
            throw new VolunteerRuntimeException(ResponseEnum.VERIFY_MSG_CODE_INVALID);
        }

        return userDao.updatePassword(tel, newPassword) > 0;
    }

    @Override
    public boolean getVerifyMsgCode(String tel) {
        if (StringUtils.isNotBlank(cache.getIfPresent(tel))) {
            return true;
        }
        String msgCode = msgUtil.sendSignUpMsgCode(tel);
        cache.put(tel, msgCode);

        return false;
    }

    @Override
    public boolean deleteUserByUserId(long userId){
        UserDTO userDTO=userDao.getUserByUserId(userId);
        if (userDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USER_NOT_FOUND);
        }
        return userDao.deleteByUserId(userId) > 0;
    }
}
