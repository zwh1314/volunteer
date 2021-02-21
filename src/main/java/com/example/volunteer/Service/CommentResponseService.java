package com.example.volunteer.Service;

import com.example.volunteer.Entity.CommentResponse;
import com.example.volunteer.Request.CommentResponseRequest;

import java.util.List;

public interface CommentResponseService {
    boolean addCommentResponse(CommentResponseRequest commentResponseRequest);

    boolean updateResponseLikeNumber(long responseLikeNumber, long responseId);

    boolean updateResponseText(String responseText, long responseId);

    List<CommentResponse> getCommentResponseByCommentId(long commentId);

    List<CommentResponse> getVideoCommentResponseByCommentId(long commentId);

    List<CommentResponse> getCommentResponseInOneWeek();

    long getResponseLikeByResponseId(long responseId);

    boolean likesResponse(long responseId);

    List<CommentResponse> getVideoCommentResponseInOneWeek();

    List<CommentResponse> getCommentResponseByRelativeText(String relativeText);

    List<CommentResponse> getVideoCommentResponseByRelativeText(String relativeText);

    List<CommentResponse> getCommentResponseByPublisherId(long publisherId);

    List<CommentResponse> getVideoCommentResponseByPublisherId(long publisherId);

    boolean deleteCommentResponseById(long responseId);
}
