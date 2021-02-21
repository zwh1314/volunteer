package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.ActivityDao;
import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Entity.Activity;
import com.example.volunteer.Response.Response;
import com.example.volunteer.enums.ResponseEnum;

import com.example.volunteer.Service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityDao activityDao;


    @Override
    public Response<Boolean> addActivity(Activity activity){
        Response<Boolean> response = new Response<>();
        boolean result = activityDao.insertActivity(activity) > 0;
        if(!result){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;

    }

    @Override
    public Response<Boolean> updateActivity(Activity activity) {
        Response<Boolean> response=new Response<>();
        boolean result = activityDao.updateActivity(activity) > 0;
        if(!result){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<ActivityDTO> getActivityByActivityId(long activityId) {
        Response<ActivityDTO> response=new Response<>();

        ActivityDTO activityDTO = activityDao.getActivityByActivityId(activityId);
        if (activityDTO == null) {
            response.setFail(ResponseEnum.FAIL);
        }
        else {
            response.setSuc(activityDTO);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteActivityByActivityId(long activityId){
        Response<Boolean> response=new Response<>();

        ActivityDTO activityDTO=activityDao.getActivityByActivityId(activityId);
        if (activityDTO == null) {
            response.setFail(ResponseEnum.FAIL);
        }
        else {
            boolean result=activityDao.deleteActivityByActivityId(activityId) > 0;
            if(!result){
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
            }
            else {
                response.setSuc(true);
            }
        }
        return response;
    }
}
