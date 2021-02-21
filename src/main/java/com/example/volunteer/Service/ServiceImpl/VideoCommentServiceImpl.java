package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.VideoCommentDao;
import com.example.volunteer.Entity.VideoComment;
import com.example.volunteer.Request.VideoCommentRequest;
import com.example.volunteer.Response.Response;
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
    public Response<Boolean> addVideoComment(VideoCommentRequest videoCommentRequest){
        Response<Boolean> response=new Response<>();

        for(VideoComment videoComment:videoCommentRequest.getVideoCommentList()) {
            boolean result = videoCommentDao.addComment(videoComment) > 0;
            if (!result) {
                logger.error("[addComment Fail], request: {}", SerialUtil.toJsonStr(videoCommentRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> updateVideoCommentLikeNumber(long commentLikeNumber, long commentId){
        Response<Boolean> response=new Response<>();

        boolean result = videoCommentDao.updateCommentLike(commentLikeNumber, commentId) > 0;
        if (!result) {
            logger.error("[updateCommentLike Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateVideoCommentText(String commentText, long commentId){
        Response<Boolean> response=new Response<>();

        boolean result = videoCommentDao.updateCommentText(commentText,commentId) > 0;
        if (!result) {
            logger.error("[updateCommentText Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<VideoComment>> getVideoCommentByPublisher(long publisherId){
        Response<List<VideoComment>> response=new Response<>();

        List<VideoComment> videoCommentList = videoCommentDao.findCommentById(publisherId);
        if (videoCommentList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        else {
            response.setSuc(videoCommentList);
        }
        return response;
    }

    @Override
    public Response<List<VideoComment>> getVideoCommentInOneWeek(){
        Response<List<VideoComment>> response=new Response<>();

        List<VideoComment> videoCommentList = videoCommentDao.findCommentInOneWeek();
        if (videoCommentList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        else{
            response.setSuc(videoCommentList);
        }
        return response;
    }

    @Override
    public Response<List<VideoComment>> getVideoCommentByRelativeText(String relativeText){
        Response<List<VideoComment>> response=new Response<>();

        List<VideoComment> videoCommentList = videoCommentDao.findCommentByText(relativeText);
        if (videoCommentList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else{
            response.setSuc(videoCommentList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteVideoCommentById(long commentId){
        Response<Boolean> response=new Response<>();

        boolean result=videoCommentDao.deleteCommentById(commentId) > 0;
        if(!result){
            logger.error("[deleteCommentById Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
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
