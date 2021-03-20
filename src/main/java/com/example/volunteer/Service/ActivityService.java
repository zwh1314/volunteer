package com.example.volunteer.Service;

import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Entity.Activity;
import com.example.volunteer.Response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {

    /**增加活动
     * @param activity
     * @return成功返回true，失败返回false
     */
    Response<Boolean> addActivity(Activity activity);

    /**更新活动
     * @param activity
     * @return成功返回true，失败返回false
     */
    Response<Boolean> updateActivity(Activity activity);


    /**
     * 通过id查询活动
     * @param activityId
     * @return
     */
    public Response<ActivityDTO> getActivityByActivityId(long activityId);


    /**删除活动
     * @param activityId
     * @return成功返回true，失败返回false
     */
    Response<Boolean> deleteActivityByActivityId(long activityId);
}
