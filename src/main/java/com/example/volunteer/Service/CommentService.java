package com.example.volunteer.Service;

import com.example.volunteer.DTO.CommentDTO;
import com.example.volunteer.Entity.Comment;
import com.example.volunteer.Request.CommentRequest;
import com.example.volunteer.Response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommentService {
    Response<Boolean> addComment(long userId, String commentText, MultipartFile[] commentPicture);

    Response<Boolean> updateCommentLikeNumber(long commentLikeNumber, long commentId);

    Response<Boolean> updateCommentText(String commentText, long commentId);

    Response<List<Comment>> getCommentByPublisher(long publisherId);

    Response<List<Comment>> getCommentInOneWeek();

    Response<List<Comment>> getCommentByRelativeText(String relativeText);

    Response<Boolean> deleteCommentById(long commentId);

    Response<Boolean> addCommentPicture(long commentId, MultipartFile[] commentPicture);

    Response<List<CommentDTO>> getCommentByNumber(long number);

}
