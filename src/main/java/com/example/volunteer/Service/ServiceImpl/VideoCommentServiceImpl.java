package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.VideoCommentDao;
import com.example.volunteer.Entity.VideoComment;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.VideoCommentRequest;
import com.example.volunteer.Service.VideoCommentService;
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
public class VideoCommentServiceImpl implements VideoCommentService {
    private static final Logger logger = LoggerFactory.getLogger(VideoCommentServiceImpl.class);

    @Autowired
    private VideoCommentDao videoCommentDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean addVideoComment(VideoCommentRequest videoCommentRequest){
        boolean result;

        for(VideoComment videoComment:videoCommentRequest.getVideoCommentList()) {
            result = videoCommentDao.addComment(videoComment) > 0;
            if (!result) {
                logger.error("[addComment Fail], request: {}", SerialUtil.toJsonStr(videoCommentRequest));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateVideoCommentLikeNumber(long commentLikeNumber, long commentId){
        boolean result;
        result = videoCommentDao.updateCommentLike(commentLikeNumber, commentId) > 0;
        if (!result) {
            logger.error("[updateCommentLike Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
        }
        return result;
    }

    @Override
    public boolean updateVideoCommentText(String commentText, long commentId){
        boolean result;
        result = videoCommentDao.updateCommentText(commentText,commentId) > 0;
        if (!result) {
            logger.error("[updateCommentText Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
        }
        return result;
    }

    @Override
    public List<VideoComment> getVideoCommentByPublisher(long publisherId){
        List<VideoComment> videoCommentList = videoCommentDao.findCommentById(publisherId);
        if (videoCommentList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return videoCommentList;
    }

    @Override
    public List<VideoComment> getVideoCommentInOneWeek(){
        List<VideoComment> videoCommentList = videoCommentDao.findCommentInOneWeek();
        if (videoCommentList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return videoCommentList;
    }

    @Override
    public List<VideoComment> getVideoCommentByRelativeText(String relativeText){
        List<VideoComment> videoCommentList = videoCommentDao.findCommentByText(relativeText);
        if (videoCommentList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return videoCommentList;
    }

    @Override
    public boolean deleteVideoCommentById(long commentId){
        boolean result;

        result=videoCommentDao.deleteCommentById(commentId) > 0;
        if(!result){
            logger.error("[deleteCommentById Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
        }
        return result;
    }

    public static String VIDEO_COMMENT_LIKE_KEY(long commentId){
        return "redis:videoCommentLike:" + commentId;
    }


    private Long getCommentLikeFromRedis(long commentId){

        Long commentLike;
        try{
            Object o = redisUtil.get(VIDEO_COMMENT_LIKE_KEY(commentId));
            if (o == null)
                return null;
            else commentLike = Long.valueOf(String.valueOf(o));
        }catch (Exception e){
            logger.error("[getCommentLikeFromRedis Fail], commentId:{}",SerialUtil.toJsonStr(commentId));
            e.printStackTrace();
            return null;
        }
        return commentLike;
    }


    @Override
    public long getCommentLikeByCommentId(long commentId) {
        Long like = getCommentLikeFromRedis(commentId);
        if (like != null) {
            return like;
        }
        like  = Optional.ofNullable(videoCommentDao.getCommentLikeByCommentId(commentId)).orElse(0L);
        redisUtil.set(VIDEO_COMMENT_LIKE_KEY(commentId),like);
        return like;
    }

    @Override
    public boolean LikesComment(long commentId) {
        boolean result;
        Long like = getCommentLikeFromRedis(commentId);
        if(like != null){
            result = redisUtil.set(VIDEO_COMMENT_LIKE_KEY(commentId),like+1);
        }else{
            like = Optional.ofNullable(videoCommentDao.getCommentLikeByCommentId(commentId)).orElse(0L);
            result =  redisUtil.set(VIDEO_COMMENT_LIKE_KEY(commentId),like+1);
        }
        return result;
    }
}
