package com.example.volunteer.RedisService.RedisServiceImpl;

import com.example.volunteer.Dao.CommentResponseDao;
import com.example.volunteer.RedisService.ResponseRedisService;
import com.example.volunteer.Service.ServiceImpl.CommentResponseServiceImpl;
import com.example.volunteer.utils.RedisUtil;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseRedisDaoImpl implements ResponseRedisService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CommentResponseDao commentResponseDao;

    private static final Logger logger = LoggerFactory.getLogger(ResponseRedisDaoImpl.class);

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void responseLikeSchedule(){
        List<Long> responseIds = commentResponseDao.getResponseIds();
        Long like;
        for (Long responseId : responseIds) {
            try {

                Object o = redisUtil.get(CommentResponseServiceImpl.RESPONSE_LIKE_KEY(responseId));
                if (o != null) {
                    like = Long.valueOf(String.valueOf(o));
                    commentResponseDao.updateResponseLike(like, responseId);
                }
            } catch (Exception e) {
                logger.warn("[Redis is not maintained], responseId:{}", SerialUtil.toJsonStr(responseId),e);
            }
        }
    }
}
