package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.VideoDao;
import com.example.volunteer.Entity.Video;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.VideoRequest;
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
    public boolean addVideo(VideoRequest videoRequest){
        boolean result;

        for(Video video:videoRequest.getVideoList()) {
            result = videoDao.addVideo(video) > 0;
            if (!result) {
                logger.error("[addVideo Fail], request: {}", SerialUtil.toJsonStr(videoRequest));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateVideoTextContent(String textContent, long videoId){
        boolean result;
        result = videoDao.updateVideoText(textContent,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoText Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
        }
        return result;
    }

    @Override
    public boolean updateVideoLikeNumber(long likeNumber, long videoId){
        boolean result;
        result = videoDao.updateVideoLike(likeNumber,videoId) > 0;
        if (!result) {
            logger.error("[updateVideoLike Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
        }
        return result;
    }

    @Override
    public List<Video> getVideoByPublisherId(long publisherId){
        List<Video> videoList = videoDao.findVideoByUser(publisherId);
        if (videoList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return videoList;
    }

    @Override
    public List<Video> getVideoByRelativeText(String relativeText){
        List<Video> videoList = videoDao.findVideoByText(relativeText);
        if (videoList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return videoList;
    }

    @Override
    public List<Video> getVideoInOneWeek(){
        List<Video> videoList = videoDao.findVideoInOneWeek();
        if (videoList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return videoList;
    }

    @Override
    public boolean deleteVideoById(long videoId){
        boolean result;

        result=videoDao.deleteVideoById(videoId) > 0;
        if(!result){
            logger.error("[deleteVideoById Fail], videoId: {}", SerialUtil.toJsonStr(videoId));
        }
        return result;
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
