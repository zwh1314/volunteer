package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.ActivityUserDao;
import com.example.volunteer.Entity.ActivityUser;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.ActivityUserService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityUserServiceImpl implements ActivityUserService {
    private static final Logger logger = LoggerFactory.getLogger(ActivityNewsServiceImpl.class);

    @Autowired
    private ActivityUserDao activityUserDao;

    @Override
    public Response<Boolean> addActivityUser(ActivityUser activityUser) {
        Response<Boolean> response=new Response<>();
        boolean result = activityUserDao.addActivityUser(activityUser) > 0;
        if(!result){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateFormStatusByUserId(String formStatus, long userId) {
        Response<Boolean> response=new Response<>();

        boolean result = activityUserDao.updateFormStatusByUserId(formStatus, userId) > 0;
        if(!result){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else
        {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateFormDateByUserId(Date formDate, long userId) {
        Response<Boolean> response=new Response<>();

        boolean result = activityUserDao.updateFormDateByUserId(formDate, userId) > 0;
        if(!result){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<ActivityUser>> getActivityUserByUserId(long userId) {
        Response<List<ActivityUser>> response=new Response<>();

        List<ActivityUser> activityUserList = activityUserDao.findActivityUserByUserId(userId);
        if (activityUserList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else{
            response.setSuc(activityUserList);
        }
        return response;
    }

    @Override
    public Response<List<ActivityUser>> getActivityUserByActivityId(long activityId) {
        Response<List<ActivityUser>> response=new Response<>();

        List<ActivityUser> activityUserList = activityUserDao.findActivityUserByActivityId(activityId);
        if (activityUserList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else{
            response.setSuc(activityUserList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteActivityUserByActivityId(long activityId) {
        Response<Boolean> response=new Response<>();

        boolean result=activityUserDao.deleteActivityUserByActivityId(activityId) > 0;
        if(!result){
            logger.error("[deleteActivityUserByActivityId Fail], activityId: {}", SerialUtil.toJsonStr(activityId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteActivityUserByUserId(long userId) {
        Response<Boolean> response=new Response<>();

        boolean result=activityUserDao.deleteActivityUserByUserId(userId) > 0;
        if(!result){
            logger.error("[deleteActivityUserByUserId Fail], userId: {}", SerialUtil.toJsonStr(userId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }
}
