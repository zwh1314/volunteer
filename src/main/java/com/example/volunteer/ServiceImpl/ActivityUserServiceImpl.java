package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Dao.ActivityNewsDao;
import com.example.volunteer.Dao.ActivityUserDao;
import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Entity.ActivityUser;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.ActivityNewsRequest;
import com.example.volunteer.Service.ActivityNewsService;
import com.example.volunteer.Service.ActivityUserService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
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
    public boolean addActivityUser(ActivityUser activityUser) {
        boolean result=true;
        result = activityUserDao.addActivityUser(activityUser) > 0;
        return result;
    }

    @Override
    public boolean updateFormStatusByUserId(String formStatus, long userId) {
        boolean result=true;
        result = activityUserDao.updateFormStatusByUserId(formStatus, userId) > 0;
        return result;
    }

    @Override
    public boolean updateFormDateByUserId(Date formDate, long userId) {
        boolean result=true;
        result = activityUserDao.updateFormDateByUserId(formDate, userId) > 0;
        return result;
    }

    @Override
    public List<ActivityUser> getActivityUserByUserId(long userId) {

        List<ActivityUser> activityUserList = activityUserDao.findActivityUserByUserId(userId);
        if (activityUserList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return activityUserList;
    }

    @Override
    public List<ActivityUser> getActivityUserByActivityId(long activityId) {

        List<ActivityUser> activityUserList = activityUserDao.findActivityUserByActivityId(activityId);
        if (activityUserList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return activityUserList;
    }

    @Override
    public boolean deleteActivityUserByActivityId(long activityId) {
        boolean result;

        result=activityUserDao.deleteActivityUserByActivityId(activityId) > 0;
        if(!result){
            logger.error("[deleteActivityUserByActivityId Fail], activityId: {}", SerialUtil.toJsonStr(activityId));
        }
        return result;
    }

    @Override
    public boolean deleteActivityUserByUserId(long userId) {
        boolean result;

        result=activityUserDao.deleteActivityUserByUserId(userId) > 0;
        if(!result){
            logger.error("[deleteActivityUserByUserId Fail], userId: {}", SerialUtil.toJsonStr(userId));
        }
        return result;
    }
}
