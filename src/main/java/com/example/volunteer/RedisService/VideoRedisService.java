package com.example.volunteer.RedisService;

import com.example.volunteer.Response.Response;

public interface VideoRedisService {
    String videoLikeKey(long videoId);

    Long getVideoLikeFromRedis(long videoId);

    Response<Boolean> likesVideo(long videoId);

    Response<Long> getVideoLikeByVideoId(long videoId);
}
