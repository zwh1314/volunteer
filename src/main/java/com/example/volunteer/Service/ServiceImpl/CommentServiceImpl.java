package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.CommentDao;
import com.example.volunteer.Entity.Comment;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.CommentRequest;
import com.example.volunteer.Service.CommentService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    @Override
    public boolean addComment(CommentRequest commentRequest){
        boolean result;

        for(Comment comment:commentRequest.getCommentList()) {
            result = commentDao.addComment(comment) > 0;
            if (!result) {
                logger.error("[addComment Fail], request: {}", SerialUtil.toJsonStr(commentRequest));
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateCommentLikeNumber(long commentLikeNumber, long commentId){
        boolean result;
        result = commentDao.updateCommentLike(commentLikeNumber, commentId) > 0;
        if (!result) {
            logger.error("[updateCommentLike Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
        }
        return result;
    }

    @Override
    public boolean updateCommentText(String commentText, long commentId){
        boolean result;
        result = commentDao.updateCommentText(commentText,commentId) > 0;
        if (!result) {
            logger.error("[updateCommentText Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
        }
        return result;
    }

    @Override
    public List<Comment> getCommentByPublisher(long publisherId){
        List<Comment> commentList = commentDao.findCommentById(publisherId);
        if (commentList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_PUBLISHER_NOT_FOUND);
        }
        return commentList;
    }

    @Override
    public List<Comment> getCommentInOneWeek(){
        List<Comment> commentList = commentDao.findCommentInOneWeek();
        if (commentList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_IN_ONE_WEEK_NOT_FOUND);
        }
        return commentList;
    }

    @Override
    public List<Comment> getCommentByRelativeText(String relativeText){
        List<Comment> commentList = commentDao.findCommentByText(relativeText);
        if (commentList == null) {
            throw new VolunteerRuntimeException(ResponseEnum.OBJECT_RELATIVE_TEXT_NOT_FOUND);
        }
        return commentList;
    }

    @Override
    public boolean deleteCommentById(long commentId){
        boolean result;

        result=commentDao.deleteCommentById(commentId) > 0;
        if(!result){
            logger.error("[deleteCommentById Fail], commentId: {}", SerialUtil.toJsonStr(commentId));
        }
        return result;
    }
}
