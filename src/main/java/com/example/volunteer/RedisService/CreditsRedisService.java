package com.example.volunteer.RedisService;

import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Entity.UserInfo;
import com.example.volunteer.Response.Response;

import java.util.List;
import java.util.Map;

public interface CreditsRedisService {

    /**
     * 更新积分
     */
    Response<Boolean> updateCredits(String userId, int change);

    /**
     * 查询积分
     */
    int getCredits(String userId);

    /**
     * 从redis里查询积分
     */
    Integer getCreditsFromRedis(String userId);



}

