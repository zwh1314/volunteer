package com.example.volunteer.RedisService.RedisServiceImpl;

import com.example.volunteer.Dao.VideoDao;
import com.example.volunteer.RedisService.VideoRedisService;
import com.example.volunteer.Service.ServiceImpl.VideoServiceImpl;
import com.example.volunteer.utils.RedisUtil;
import com.example.volunteer.utils.SerialUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoRedisDaoImpl implements VideoRedisService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    VideoDao videoDao;

    private static final Logger logger = LoggerFactory.getLogger(VideoRedisDaoImpl.class);

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void videoLikeSchedule(){
        List<Long> videoIds = videoDao.getVideoIds();
        Long like;
        for (Long videoId : videoIds) {
            try {

                Object o = redisUtil.get(VideoServiceImpl.VIDEO_LIKE_KEY(videoId));
                if (o != null) {
                    like = Long.valueOf(String.valueOf(o));
                    videoDao.updateVideoLike(like,videoId);
                }
            } catch (Exception e) {
                logger.warn("[Redis is not maintained], videoId:{}", SerialUtil.toJsonStr(videoId),e);
            }
        }
    }
}
