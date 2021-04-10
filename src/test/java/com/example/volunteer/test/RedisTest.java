package com.example.volunteer.test;

import com.example.volunteer.Entity.User;
import com.example.volunteer.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest(){
        User user=new User();
        user.setUserId(1);
        //user.setUserName("张文瀚");
        user.setPassword("991219");
        user.setPriority("超级管理者");
        user.setTel("15082361803");
        redisUtil.set("张文瀚",user);
        System.out.println(redisUtil.get("张文瀚"));
    }
}
