package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.ActivityNewsDao;
import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.ActivityNewsRequest;
import com.example.volunteer.Service.ActivityNewsService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityNewsServiceImpl implements ActivityNewsService {
    private static final Logger logger = LoggerFactory.getLogger(ActivityNewsServiceImpl.class);

    @Autowired
    private ActivityNewsDao activityNewsDao;

    @Override
    public boolean addActivityNews(ActivityNewsRequest activityNewsRequest){
        boolean result;

        for(ActivityNews activityNews:activityNewsRequest.getActivityNewsList()) {
            result = activityNewsDao.addActivityNews(activityNews) > 0;
            if (!result) {
                logger.error("[addActivityNews Fail], request: {}", SerialUtil.toJsonStr(activityNewsRequest));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateActivityNewsContent(String activityNewsContent, long newsId){
        boolean result;
        result = activityNewsDao.updateActivityNewsContent(activityNewsContent,newsId) > 0;
        if (!result) {
            logger.error("[updateActivityNewsContent Fail], newsId: {}", SerialUtil.toJsonStr(newsId));
        }
        return result;
    }

    @Override
    public boolean updateActivityNewsPicture(String activityNewsPicture, long newsId){
        boolean result;
        result = activityNewsDao.updateActivityNewsPicture(activityNewsPicture,newsId) > 0;
        if (!result) {
            logger.error("[updateActivityNewsPicture Fail], newsId: {}", SerialUtil.toJsonStr(newsId));
        }
        return result;
    }

    @Override
    public boolean updateActivityNewsTitle(String activityNewsTitle, long newsId){
        boolean result;
        result = activityNewsDao.updateActivityNewsTitle(activityNewsTitle,newsId) > 0;
        if (!result) {
            logger.error("[updateActivityNewsTitle Fail], newsId: {}", SerialUtil.toJsonStr(newsId));
        }
        return result;
    }

    @Override
    public List<ActivityNews> getActivityNewsByActivityId(long activityId){
        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsById(activityId);
        if (activityNewsList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.ACTIVITY_NEWS_ACTIVITY_NOT_FOUND);
        }
        return activityNewsList;
    }

    @Override
    public List<ActivityNews> getActivityNewsByPublisherId(long publisherId){
        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsByPublisher(publisherId);
        if (activityNewsList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return activityNewsList;
    }

    @Override
    public List<ActivityNews> getActivityNewsInOneWeek(){
        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsInOneWeek();
        if (activityNewsList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return activityNewsList;
    }

    @Override
    public List<ActivityNews> getActivityNewsByRelativeText(String relativeText){
        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsByContent(relativeText);
        if (activityNewsList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return activityNewsList;
    }

    @Override
    public boolean deleteActivityNewsById(long activityNewsId){
        boolean result;

        result=activityNewsDao.deleteActivityNewsById(activityNewsId) > 0;
        if(!result){
            logger.error("[deleteActivityNewsById Fail], activityNewsId: {}", SerialUtil.toJsonStr(activityNewsId));
        }
        return result;
    }
}
