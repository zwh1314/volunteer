package com.example.volunteer.RedisService.RedisServiceImpl;

import com.example.volunteer.Dao.VideoCommentDao;
import com.example.volunteer.RedisService.VideoCommentRedisService;
import com.example.volunteer.Service.ServiceImpl.VideoCommentServiceImpl;
import com.example.volunteer.utils.RedisUtil;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoCommentRedisDaoImpl implements VideoCommentRedisService {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    VideoCommentDao videoCommentDao;

    private static final Logger logger = LoggerFactory.getLogger(VideoCommentRedisDaoImpl.class);

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void commentLikeSchedule(){
        List<Long> commentIds = videoCommentDao.getCommentIds();
        Long like;
        for (Long commentId : commentIds) {
            try {
                Object o = redisUtil.get(VideoCommentServiceImpl.VIDEO_COMMENT_LIKE_KEY(commentId));
                if (o != null) {
                    like = Long.valueOf(String.valueOf(o));
                    videoCommentDao.updateCommentLike(like, commentId);
                }
            } catch (Exception e) {
                logger.warn("[Redis is not maintained], commentId:{}", SerialUtil.toJsonStr(commentId),e);
            }
        }
    }
}
