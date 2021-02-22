package com.example.volunteer.RedisService;

import com.example.volunteer.Response.Response;

public interface ResponseRedisService {
    String responseLikeKey(long responseId);

    Long getResponseLikeFromRedis(long responseId);

    Response<Boolean> likesResponse(long responseId);

    Response<Long> getResponseLikeByResponseId(long responseId);
}
