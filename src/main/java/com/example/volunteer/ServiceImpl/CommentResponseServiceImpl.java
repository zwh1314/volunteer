package com.example.volunteer.ServiceImpl;

import com.example.volunteer.Dao.CommentResponseDao;
import com.example.volunteer.Entity.CommentResponse;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.CommentResponseRequest;
import com.example.volunteer.Service.CommentResponseService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentResponseServiceImpl implements CommentResponseService {
    private static final Logger logger = LoggerFactory.getLogger(CommentResponseServiceImpl.class);

    @Autowired
    private CommentResponseDao commentResponseDao;

    @Override
    public boolean addCommentResponse(CommentResponseRequest commentResponseRequest){
        boolean result;

        for(CommentResponse commentResponse:commentResponseRequest.getCommentResponseList()) {
            result = commentResponseDao.addCommentResponse(commentResponse) > 0;
            if (!result) {
                logger.error("[addCommentResponse Fail], request: {}", SerialUtil.toJsonStr(commentResponseRequest));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateResponseLikeNumber(long responseLikeNumber, long responseId){
        boolean result;
        result = commentResponseDao.updateResponseLike(responseLikeNumber,responseId) > 0;
        if (!result) {
            logger.error("[updateResponseLike Fail], responseId: {}", SerialUtil.toJsonStr(responseId));
        }
        return result;
    }

    @Override
    public boolean updateResponseText(String responseText, long responseId){
        boolean result;
        result = commentResponseDao.updateResponseText(responseText,responseId) > 0;
        if (!result) {
            logger.error("[updateResponseText Fail], responseId: {}", SerialUtil.toJsonStr(responseId));
        }
        return result;
    }

    @Override
    public List<CommentResponse> getCommentResponseByCommentId(long commentId){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByComment(commentId, 0L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.COMMENT_RESPONSE_COMMENT_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getVideoCommentResponseByCommentId(long commentId){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByComment(commentId, 1L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.COMMENT_RESPONSE_VIDEO_COMMENT_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getCommentResponseInOneWeek(){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseInOneWeek(1L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getVideoCommentResponseInOneWeek(){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseInOneWeek(0L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getCommentResponseByRelativeText(String relativeText){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByText(relativeText, 0L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getVideoCommentResponseByRelativeText(String relativeText){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByText(relativeText, 1L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getCommentResponseByPublisherId(long publisherId){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByPublisher(publisherId, 0L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getVideoCommentResponseByPublisherId(long publisherId){
        List<CommentResponse> commentResponseList = commentResponseDao.findCommentResponseByPublisher(publisherId,1L);
        if (commentResponseList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return commentResponseList;
    }

    @Override
    public boolean deleteCommentResponseById(long responseId){
        boolean result;

        result= commentResponseDao.deleteCommentResponseById(responseId) > 0;
        if(!result){
            logger.error("[deleteCommentResponseById Fail], responseId: {}", SerialUtil.toJsonStr(responseId));
        }
        return result;
    }
}
