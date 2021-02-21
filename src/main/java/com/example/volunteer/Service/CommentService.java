package com.example.volunteer.Service;

import com.example.volunteer.Entity.Comment;
import com.example.volunteer.Request.CommentRequest;

import java.util.List;

public interface CommentService {
    boolean addComment(CommentRequest commentRequest);

    boolean updateCommentLikeNumber(long commentLikeNumber, long commentId);

    boolean updateCommentText(String commentText, long commentId);

    List<Comment> getCommentByPublisher(long publisherId);

    List<Comment> getCommentInOneWeek();

    List<Comment> getCommentByRelativeText(String relativeText);

    boolean deleteCommentById(long commentId);

    long getCommentLikeByCommentId(long commentId);

    boolean LikesComment(long commentId);
}
