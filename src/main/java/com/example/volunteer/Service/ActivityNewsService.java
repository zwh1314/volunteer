package com.example.volunteer.Service;

import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Request.ActivityNewsRequest;

import java.util.List;

public interface ActivityNewsService {
    boolean addActivityNews(ActivityNewsRequest activityNewsRequest);

    boolean updateActivityNewsContent(String activityNewsContent, long newsId);

    boolean updateActivityNewsPicture(String activityNewsPicture, long newsId);

    boolean updateActivityNewsTitle(String activityNewsTitle, long newsId);

    List<ActivityNews> getActivityNewsByActivityId(long activityId);

    List<ActivityNews> getActivityNewsByPublisherId(long publisherId);

    List<ActivityNews> getActivityNewsInOneWeek();

    List<ActivityNews> getActivityNewsByRelativeText(String relativeText);

    boolean deleteActivityNewsById(long activityNewsId);
}
