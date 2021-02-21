package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.CommentResponseDao;
import com.example.volunteer.Entity.CommentResponse;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.CommentResponseRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.CommentResponseService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.RedisUtil;
import com.example.volunteer.utils.SerialUtil;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentResponseServiceImpl implements CommentResponseService {
    private static final Logger logger = LoggerFactory.getLogger(CommentResponseServiceImpl.class);

    @Autowired
    private CommentResponseDao commentResponseDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Response<Boolean> addCommentResponse(CommentResponseRequest commentResponseRequest){
        Response<Boolean> response=new Response<>();

        for(CommentResponse commentResponse:commentResponseRequest.getCommentResponseList()) {
            boolean result = commentResponseDao.addCommentResponse(commentResponse) > 0;
            if (!result) {
                logger.error("[addCommentResponse Fail], request: {}", SerialUtil.toJsonStr(commentResponseRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> updateResponseLikeNumber(long responseLikeNumber, long responseId){
        Response<Boolean> response=new Response<>();
        boolean result = commentResponseDao.updateResponseLike(responseLikeNumber,responseId) > 0;
        if (!result) {
            logger.error("[updateResponseLike Fail], responseId: {}", SerialUtil.toJsonStr(responseId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<Boolean> updateResponseText(String responseText, long responseId){
        Response<Boolean>  response=new Response<>();

        boolean result = commentResponseDao.updateResponseText(responseText,responseId) > 0;
        if (!result) {
            logger.error("[updateResponseText Fail], responseId: {}", SerialUtil.toJsonStr(responseId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else {
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getCommentResponseByCommentId(long commentId){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByComment(commentId, 0L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.COMMENT_RESPONSE_COMMENT_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getVideoCommentResponseByCommentId(long commentId){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByComment(commentId, 1L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.COMMENT_RESPONSE_VIDEO_COMMENT_NOT_FOUND);
        }
        else {
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getCommentResponseInOneWeek(){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseInOneWeek(1L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getVideoCommentResponseInOneWeek(){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseInOneWeek(0L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getCommentResponseByRelativeText(String relativeText){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByText(relativeText, 0L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getVideoCommentResponseByRelativeText(String relativeText){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByText(relativeText, 1L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getCommentResponseByPublisherId(long publisherId){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByPublisher(publisherId, 0L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<List<CommentResponse>> getVideoCommentResponseByPublisherId(long publisherId){
        Response<List<CommentResponse>> response=new Response<>();

        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByPublisher(publisherId,1L);
        if (commentResponseList.size() == 0) {
            response.setFail(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        else{
            response.setSuc(commentResponseList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteCommentResponseById(long responseId){
        Response<Boolean> response=new Response<>();

        boolean result= commentResponseDao.deleteCommentResponseById(responseId) > 0;
        if(!result){
            logger.error("[deleteCommentResponseById Fail], responseId: {}", SerialUtil.toJsonStr(responseId));
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    public static String RESPONSE_LIKE_KEY(long responseId){
        return "redis:responseLike:" + responseId;
    }

    private Long getResponseLikeFromRedis(long responseId){
        Long responseLike;
        try{
            Object o = redisUtil.get(RESPONSE_LIKE_KEY(responseId));
            if (o == null)
                return null;
            else responseLike = Long.valueOf(String.valueOf(o));
        }catch (Exception e){
            logger.error("[getResponseLikeFromRedis Fail], responseId：{}",SerialUtil.toJsonStr(responseId));
            e.printStackTrace();
            return  null;
        }
        return responseLike;
    }

    @Override
    public long getResponseLikeByResponseId(long responseId){
        Long like = getResponseLikeFromRedis(responseId);
        if(like != null){
            return like;
        }
        like  = Optional.ofNullable(commentResponseDao.getResponseLikeByResponseId(responseId)).orElse(0L);
        redisUtil.set(RESPONSE_LIKE_KEY(responseId),like);
        return like;
    }

    @Override
    public boolean likesResponse(long responseId) {
        boolean result;
        Long like = getResponseLikeFromRedis(responseId);
        if (like != null){
            result = redisUtil.set(RESPONSE_LIKE_KEY(responseId),like+1);
        }else{
            like = Optional.ofNullable(commentResponseDao.getResponseLikeByResponseId(responseId)).orElse(0L);
            result =  redisUtil.set(RESPONSE_LIKE_KEY(responseId),like+1);
        }
        return result;
    }
}
