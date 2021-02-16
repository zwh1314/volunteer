package com.example.volunteer.RedisService.RedisServiceImpl;

import com.example.volunteer.Dao.UserInfoDao;
import com.example.volunteer.RedisService.CreditsRedisService;
import com.example.volunteer.Response.Response;
import com.example.volunteer.utils.RedisUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class CreditsRedisServiceImpl implements CreditsRedisService {

    private Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .initialCapacity(5)
            .maximumSize(25)
            .build();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserInfoDao userinfoDao;

    @Override
    public Response<Boolean> updateCredits(String userId, int change) {
        Response<Boolean> response=new Response<>();
        Integer credits = getCreditsFromRedis(userId);
        boolean result = false;

        if (credits != null) {
            result = redisUtil.hset("credit",userId, credits + change);
        } else {
            credits = Optional.ofNullable(userinfoDao.getCreditsById(Long.parseLong(userId)).getCredits()).orElse(0);
            result = redisUtil.hset("credit",userId, credits + change);
        }

        if(result)response.setSuc(true);
        return response;
    }
   @Override
    public Integer getCreditsFromRedis(String userId){
        Integer credits;
        try{
            credits = (Integer)redisUtil.hget("credit",userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return credits;
    }

    @Override
    public int getCredits(String userId) {
        Integer credits = getCreditsFromRedis(userId);
        if(credits != null)return credits;

        credits = Optional.ofNullable(userinfoDao.getCreditsById(Long.parseLong(userId)).getCredits()).orElse(0);

        redisUtil.hset("credit",userId,credits);
        return credits;

    }


}
