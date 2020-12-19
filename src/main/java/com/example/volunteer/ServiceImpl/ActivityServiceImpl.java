package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Dao.ActivityDao;
import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Entity.Activity;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.Exception.VolunteerRuntimeException;

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
    public boolean addActivity(Activity activity){
        boolean result=true;
        result = activityDao.insertActivity(activity) > 0;
        return result;

    }

    @Override
    public boolean updateActivity(Activity activity) {
        boolean result = true;
        result = activityDao.updateActivity(activity) > 0;
        return result;
    }

    @Override
    public ActivityDTO getActivityByActivityId(long activityId) {
        ActivityDTO activityDTO = activityDao.getActivityByActivityId(activityId);
        if (activityDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.FAIL);
        }

        return activityDTO;

    }

    @Override
    public boolean deleteActivityByActivityId(long activityId){
        ActivityDTO activityDTO=activityDao.getActivityByActivityId(activityId);
        if (activityDTO == null) {
            throw new VolunteerRuntimeException(ResponseEnum.FAIL);
        }
        return activityDao.deleteActivityByActivityId(activityId) > 0;
    }
}
