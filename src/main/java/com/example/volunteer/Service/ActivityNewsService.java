package com.example.volunteer.Service;

import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Request.ActivityNewsRequest;
import com.example.volunteer.Response.Response;

import java.util.List;

public interface ActivityNewsService {
    Response<Boolean> addActivityNews(ActivityNewsRequest activityNewsRequest);

    Response<Boolean> updateActivityNewsContent(String activityNewsContent, long newsId);

    Response<Boolean> updateActivityNewsPicture(String activityNewsPicture, long newsId);

    Response<Boolean> updateActivityNewsTitle(String activityNewsTitle, long newsId);

    Response<List<ActivityNews>> getActivityNewsByActivityId(long activityId);

    Response<List<ActivityNews>> getActivityNewsByPublisherId(long publisherId);

    Response<List<ActivityNews>> getActivityNewsInOneWeek();

    Response<List<ActivityNews>> getActivityNewsByRelativeText(String relativeText);

    Response<Boolean> deleteActivityNewsById(long activityNewsId);
}
