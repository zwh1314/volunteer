package com.example.volunteer.utils;

import com.example.volunteer.Dao.UserInfoDao;
import com.example.volunteer.Entity.UserInfo;
import com.example.volunteer.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class ScheduledTasks {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserInfoDao userInfoDao;



    //每隔1 day执行一次
    @Scheduled(fixedRate = 60000*60*24)
    public void reportCurrentTime() {
        Integer likes;
        try{

            Map map = new HashMap();
            map = redisUtil.hmget("credit");

            Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();

            while (entries.hasNext()) {

                Map.Entry<String, Integer> entry = entries.next();
                UserInfo userInfo = new UserInfo();
                userInfo.setCredits(entry.getValue());
                userInfo.setUserId(Long.parseLong(entry.getKey()));
                userInfoDao.updateUserInfo(userInfo);

            }


        }catch (Exception e){
        }
    }

}
