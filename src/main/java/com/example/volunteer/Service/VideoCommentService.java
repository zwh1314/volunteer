package com.example.volunteer.Service;

import com.example.volunteer.Entity.VideoComment;
import com.example.volunteer.Request.VideoCommentRequest;

import java.util.List;

public interface VideoCommentService {
    boolean addVideoComment(VideoCommentRequest videoCommentRequest);

    boolean updateVideoCommentLikeNumber(long videoCommentLikeNumber, long commentId);

    boolean updateVideoCommentText(String VideoCommentText, long commentId);

    List<VideoComment> getVideoCommentByPublisher(long publisherId);

    List<VideoComment> getVideoCommentInOneWeek();

    List<VideoComment> getVideoCommentByRelativeText(String relativeText);

    boolean deleteVideoCommentById(long videoCommentId);

    long getCommentLikeByCommentId(long commentId);

    boolean LikesComment(long commentId);
}
