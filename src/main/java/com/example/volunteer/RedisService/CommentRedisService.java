package com.example.volunteer.RedisService;

import com.example.volunteer.Response.Response;

public interface CommentRedisService {

/*    public void commentLikeSchedule();*/
    String commentLikeKey(long commentId);

    Long getCommentLikeFromRedis(long commentId);

    Response<Boolean> likesComment(long commentId);

    Response<Long> getCommentLikeByCommentId(long commentId);

}
