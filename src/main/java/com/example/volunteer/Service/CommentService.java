package com.example.volunteer.Service;

import com.example.volunteer.Entity.Comment;
import com.example.volunteer.Request.CommentRequest;
import com.example.volunteer.Response.Response;

import java.util.List;

public interface CommentService {
    Response<Boolean> addComment(CommentRequest commentRequest);

    Response<Boolean> updateCommentLikeNumber(long commentLikeNumber, long commentId);

    Response<Boolean> updateCommentText(String commentText, long commentId);

    Response<List<Comment>> getCommentByPublisher(long publisherId);

    Response<List<Comment>> getCommentInOneWeek();

    Response<List<Comment>> getCommentByRelativeText(String relativeText);

    Response<Boolean> deleteCommentById(long commentId);

}
