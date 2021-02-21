package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.ActivityNewsDao;
import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Request.ActivityNewsRequest;
import com.example.volunteer.Response.Response;
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
    public Response<Boolean> addActivityNews(ActivityNewsRequest activityNewsRequest){
        Response<Boolean> response=new Response<>();

        for(ActivityNews activityNews:activityNewsRequest.getActivityNewsList()) {
            Boolean result = activityNewsDao.addActivityNews(activityNews) > 0;
            if (!result) {
                logger.error("[addActivityNews Fail], request: {}", SerialUtil.toJsonStr(activityNewsRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> updateActivityNewsContent(String activityNewsContent, long newsId){
        Response<Boolean> response=new Response<>();
        boolean result = activityNewsDao.updateActivityNewsContent(activityNewsContent,newsId) > 0;
        if (!result) {
            logger.error("[updateActivityNewsContent Fail], newsId: {}", SerialUtil.toJsonStr(newsId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else
        {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateActivityNewsPicture(String activityNewsPicture, long newsId){
        Response<Boolean> response=new Response<>();
        boolean result = activityNewsDao.updateActivityNewsPicture(activityNewsPicture,newsId) > 0;
        if (!result) {
            logger.error("[updateActivityNewsPicture Fail], newsId: {}", SerialUtil.toJsonStr(newsId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateActivityNewsTitle(String activityNewsTitle, long newsId){
        Response<Boolean> response=new Response<>();
        boolean result = activityNewsDao.updateActivityNewsTitle(activityNewsTitle,newsId) > 0;
        if (!result) {
            logger.error("[updateActivityNewsTitle Fail], newsId: {}", SerialUtil.toJsonStr(newsId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<ActivityNews>> getActivityNewsByActivityId(long activityId){
        Response<List<ActivityNews>> response=new Response<>();

        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsById(activityId);
        if (activityNewsList.size() == 0) {
            response.setFail(ResponseEnum.ACTIVITY_NEWS_ACTIVITY_NOT_FOUND);
        }
        else{
            response.setSuc(activityNewsList);
        }
        return response;
    }

    @Override
    public Response<List<ActivityNews>> getActivityNewsByPublisherId(long publisherId){
        Response<List<ActivityNews>> response=new Response<>();

        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsByPublisher(publisherId);
        if (activityNewsList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        else{
            response.setSuc(activityNewsList);
        }
        return response;
    }

    @Override
    public Response<List<ActivityNews>> getActivityNewsInOneWeek(){
        Response<List<ActivityNews>> response=new Response<>();

        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsInOneWeek();
        if (activityNewsList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        else{
            response.setSuc(activityNewsList);
        }
        return response;
    }

    @Override
    public Response<List<ActivityNews>> getActivityNewsByRelativeText(String relativeText){
        Response<List<ActivityNews>> response=new Response<>();

        List<ActivityNews> activityNewsList = activityNewsDao.findActivityNewsByContent(relativeText);
        if (activityNewsList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else {
            response.setSuc(activityNewsList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteActivityNewsById(long activityNewsId){
        Response<Boolean> response=new Response<>();

        boolean result=activityNewsDao.deleteActivityNewsById(activityNewsId) > 0;
        if(!result){
            logger.error("[deleteActivityNewsById Fail], activityNewsId: {}", SerialUtil.toJsonStr(activityNewsId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }
}
