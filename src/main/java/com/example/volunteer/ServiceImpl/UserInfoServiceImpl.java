package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Dao.UserInfoDao;
import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Entity.UserInfo;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.UserInfoRequest;
import com.example.volunteer.Service.UserInfoService;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoDTO getUserInfoByUserId(long userId){
        UserInfoDTO userInfoDTO = userInfoDao.getUserInfoByUserId(userId);
        if (userInfoDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.USERINFO_NOT_FOUND);
        }

        return userInfoDTO;
    }

    @Override
    public boolean addUserInfo(UserInfoRequest userInfoRequest){
        boolean result=true;

        for(UserInfo userInfo:userInfoRequest.getUserInfoList()) {
            boolean addResult = userInfoDao.addUserInfo(userInfo) > 0;
            if (!addResult) {
                logger.error("[addUserInfo Fail], request: {}", SerialUtil.toJsonStr(userInfoRequest));
                result = addResult;
            }
        }
        return result;
    }

    @Override
    public boolean updateUserInfo(UserInfoRequest userInfoRequest){
        boolean result=true;

        for(UserInfo userInfo:userInfoRequest.getUserInfoList()) {
            boolean updateResult = userInfoDao.updateUserInfo(userInfo) > 0;
            if (!updateResult) {
                logger.error("[updateUserInfo Fail], request: {}", SerialUtil.toJsonStr(userInfoRequest));
                result = updateResult;
            }
        }
        return result;
    }

    @Override
    public boolean deleteUserInfoByUserId(long userId){
        boolean result=true;

        boolean deleteResult=userInfoDao.deleteUserInfoByUserId(userId) > 0;
        if(!deleteResult){
            logger.error("[deleteUserInfoByUserId Fail], request: {}", SerialUtil.toJsonStr(userId));
            result = deleteResult;
        }
        return result;
    }
}
