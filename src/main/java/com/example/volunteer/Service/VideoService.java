package com.example.volunteer.Service;

import com.example.volunteer.Entity.Video;
import com.example.volunteer.Response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
  Response<Boolean> addVideo(Video video, MultipartFile video_mp4);

  Response<Boolean> updateVideoTextContent(String textContent, long videoId);

  Response<Boolean> updateVideoLikeNumber(long likeNumber, long videoId);

  Response<List<Video>> getVideoByPublisherId(long publisherId);

  Response<List<Video>> getVideoByRelativeText(String relativeText);

  Response<List<Video>> getVideoInOneWeek();

  Response<Boolean> deleteVideoById(long videoId);

  Response<List<Video>> getVideoByNumber(long number);
}
