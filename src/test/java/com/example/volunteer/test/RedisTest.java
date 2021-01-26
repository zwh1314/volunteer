package com.example.volunteer.test;

import com.example.volunteer.Entity.User;
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

    @Test
    public void redisTest(){
        User user=new User();
        user.setUserId(1);
        user.setUserName("张文瀚");
        user.setPassword("991219");
        user.setPriority("超级管理者");
        user.setTel("15082361803");
        redisTemplate.opsForValue().set("张文瀚",user);
        System.out.println(redisTemplate.opsForValue().get("张文瀚"));
    }
}
