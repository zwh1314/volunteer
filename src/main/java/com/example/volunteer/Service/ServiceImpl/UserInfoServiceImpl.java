package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.UserInfoDao;
import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Entity.UserInfo;
import com.example.volunteer.Response.Response;
import com.example.volunteer.enums.ResponseEnum;
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
    public Response<UserInfoDTO> getUserInfoByUserId(long userId){
        Response<UserInfoDTO> response=new Response<>();

        UserInfoDTO userInfoDTO = userInfoDao.getUserInfoByUserId(userId);
        if (userInfoDTO == null) {
            response.setFail(ResponseEnum.USERINFO_NOT_FOUND);
        }
        else {
            response.setSuc(userInfoDTO);
        }

        return response;
    }

    @Override
    public Response<Boolean> addUserInfo(UserInfoRequest userInfoRequest){
        Response<Boolean> response=new Response<>();

        for(UserInfo userInfo:userInfoRequest.getUserInfoList()) {
            boolean addResult = userInfoDao.addUserInfo(userInfo) > 0;
            if (!addResult) {
                logger.error("[addUserInfo Fail], request: {}", SerialUtil.toJsonStr(userInfoRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> updateUserInfo(UserInfoRequest userInfoRequest){
        Response<Boolean> response=new Response<>();

        for(UserInfo userInfo:userInfoRequest.getUserInfoList()) {
            boolean updateResult = userInfoDao.updateUserInfo(userInfo) > 0;
            if (!updateResult) {
                logger.error("[updateUserInfo Fail], request: {}", SerialUtil.toJsonStr(userInfoRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> deleteUserInfoByUserId(long userId){
        Response<Boolean> response=new Response<>();

        boolean deleteResult=userInfoDao.deleteUserInfoByUserId(userId) > 0;
        if(!deleteResult){
            logger.error("[deleteUserInfoByUserId Fail], request: {}", SerialUtil.toJsonStr(userId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Integer> getCreditsByUserId(long userId) {
        Response<Integer> response=new Response<>();

        UserInfoDTO userInfoDTO = userInfoDao.getCreditsById(userId);
        if(null == userInfoDTO){
            logger.error("[getCreditsById Fail], request:{}",SerialUtil.toJsonStr(userId));
            response.setFail(ResponseEnum.USERINFO_NOT_FOUND);
        }
        else {
            response.setSuc(userInfoDTO.getCredits());
        }
        return response;
    }
}
