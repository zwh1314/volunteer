package com.example.volunteer.Service;

import com.example.volunteer.DTO.ActivityNewsDTO;
import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActivityNewsService {
    Response<Boolean> addActivityNews(long userId, ActivityNews activityNews);

    Response<Boolean> updateActivityNewsContent(MultipartFile activityNewsContent, long newsId);

    Response<Boolean> updateActivityNewsPicture(MultipartFile activityNewsPicture, long newsId);

    Response<Boolean> updateActivityNewsTitle(String activityNewsTitle, long newsId);

    Response<List<ActivityNewsDTO>> getActivityNewsByActivityId(long activityId);

    Response<List<ActivityNewsDTO>> getActivityNewsByPublisherId(long publisherId);

    Response<List<ActivityNewsDTO>> getActivityNewsInOneWeek();

    Response<List<ActivityNewsDTO>> getActivityNewsByRelativeText(String relativeText);

    Response<Boolean> deleteActivityNewsById(long activityNewsId);

    Response<List<ActivityNewsDTO>> getActivityNewsByNumber(long number);
}
