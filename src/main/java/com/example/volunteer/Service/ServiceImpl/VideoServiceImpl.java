package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.VideoDao;
import com.example.volunteer.Entity.Video;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.VideoRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.VideoService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.RedisUtil;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoDao videoDao;

    @Autowired
   private RedisUtil redisUtil;
    @Override
    public Response<Boolean> addVideo(VideoRequest videoRequest){
        Response<Boolean> response=new Response<>();

        for(Video video:videoRequest.getVideoList()) {
            boolean result = videoDao.addVideo(video) > 0;
            if (!result) {
                logger.error("[addVideo Fail], request: {}", SerialUtil.toJsonStr(videoRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> updateVideoTextContent(String textContent, long videoId){
        Response<Boolean> response=new Response<>();

        boolean result = videoDao.updateVideoText(textContent,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoText Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateVideoLikeNumber(long likeNumber, long videoId){
        Response<Boolean> response=new Response<>();

        boolean result = videoDao.updateVideoLike(likeNumber,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoLike Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<Video>> getVideoByPublisherId(long publisherId){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoByUser(publisherId);
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

    @Override
    public Response<List<Video>> getVideoByRelativeText(String relativeText){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoByText(relativeText);
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

    @Override
    public Response<List<Video>> getVideoInOneWeek(){
        Response<List<Video>> response=new Response<>();

        List<Video> videoList = videoDao.findVideoInOneWeek();
        if (videoList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        else {
            response.setSuc(videoList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteVideoById(long videoId){
        Response<Boolean> response=new Response<>();

        boolean result=videoDao.deleteVideoById(videoId) > 0;
        if(!result){
            logger.error("[deleteVideoById Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    public static String VIDEO_LIKE_KEY(long videoId){
        return "redis:videoLike:" + videoId;
    }

    private Long getVideoLikeFromRedis(long videoId){
        Long like;
        try{
            Object o = redisUtil.get(VIDEO_LIKE_KEY(videoId));
            if (o == null)
                return null;
            else like = Long.valueOf(String.valueOf(o));
        }catch (Exception e){
            logger.error("[getVideoLikeFromRedis Fail], videoId：{}",SerialUtil.toJsonStr(videoId));
            e.printStackTrace();
            return  null;
        }
        return like;
    }

    @Override
    public long getVideoLikeByVideoId(long videoId){
        Long like = getVideoLikeFromRedis(videoId);
        if(like != null){
            return like;
        }
        like  = Optional.ofNullable(videoDao.getVideoLikeByVideoId(videoId)).orElse(0L);
        redisUtil.set(VIDEO_LIKE_KEY(videoId),like);
        return like;
    }

    @Override
    public boolean likesVideo(long videoId) {
        boolean result;
        Long like = getVideoLikeFromRedis(videoId);
        if (like != null){
            result = redisUtil.set(VIDEO_LIKE_KEY(videoId),like+1);
        }else{
            like = Optional.ofNullable(videoDao.getVideoLikeByVideoId(videoId)).orElse(0L);
            result =  redisUtil.set(VIDEO_LIKE_KEY(videoId),like+1);
        }
        return result;
    }
}
