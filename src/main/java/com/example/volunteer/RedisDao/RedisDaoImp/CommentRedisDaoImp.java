package com.example.volunteer.RedisDao.RedisDaoImp;

import com.example.volunteer.Dao.CommentDao;
import com.example.volunteer.Dao.CommentResponseDao;
import com.example.volunteer.RedisDao.CommentRedisDao;
import com.example.volunteer.Service.ServiceImpl.CommentResponseServiceImpl;
import com.example.volunteer.Service.ServiceImpl.CommentServiceImpl;
import com.example.volunteer.utils.RedisUtil;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRedisDaoImp implements CommentRedisDao{
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CommentDao commentDao;

    private static final Logger logger = LoggerFactory.getLogger(CommentRedisDaoImp.class);

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void commentLikeSchedule(){
        List<Long> commentIds = commentDao.getCommentIds();
        Long like;
        for (Long commentId : commentIds) {
            try {

                Object o = redisUtil.get(CommentServiceImpl.COMMENT_LIKE_KEY(commentId));
                if (o != null) {
                    like = Long.valueOf(String.valueOf(o));
                    commentDao.updateCommentLike(like, commentId);
                }
            } catch (Exception e) {
                logger.warn("[Redis is not maintained], commentId:{}", SerialUtil.toJsonStr(commentId),e);
            }
        }
    }
}
