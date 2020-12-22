package com.example.volunteer.Service;

import com.example.volunteer.Entity.Video;
import com.example.volunteer.Request.VideoRequest;

import java.util.List;

public interface VideoService {
  boolean addVideo(VideoRequest videoRequest);

  boolean updateVideoTextContent(String textContent, long videoId);

  boolean updateVideoLikeNumber(long likeNumber, long videoId);

  List<Video> getVideoByPublisherId(long publisherId);

  List<Video> getVideoByRelativeText(String relativeText);

  List<Video> getVideoInOneWeek();

  boolean deleteVideoById(long videoId);
}
