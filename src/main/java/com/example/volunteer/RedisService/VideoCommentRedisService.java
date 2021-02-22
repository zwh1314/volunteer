package com.example.volunteer.RedisService;

import com.example.volunteer.Response.Response;

public interface VideoCommentRedisService {

    String videoCommentLikeKey(long commentId);

    Long getCommentLikeFromRedis(long commentId);

    Response<Boolean> likesVideoComment(long commentId);

    Response<Long> getCommentLikeByCommentId(long commentId);
}
