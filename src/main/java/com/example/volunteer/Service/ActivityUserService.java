package com.example.volunteer.Service;

import com.example.volunteer.Entity.ActivityUser;
import com.example.volunteer.Request.ActivityUserRequest;
import com.example.volunteer.Response.Response;

import java.util.Date;
import java.util.List;

public interface  ActivityUserService {
    /**增加活动
     * @param activityUserRequest
     * @return成功返回true，失败返回false
     */
    Response<Boolean> addActivityUser(ActivityUserRequest activityUserRequest);

    /**更新报名表状态
     * @param userId，formStatus
     * @return成功返回true，失败返回false
     */
    Response<Boolean> updateFormStatusByUserId(String formStatus,long userId);

    /**更新交表日期
     * @param userId，formDate
     * @return成功返回true，失败返回false
     */
    Response<Boolean> updateFormDateByUserId(Date formDate, long userId);
    /**
     * 通过id查询活动
     * @param userId
     * @return
     */
    Response<List<ActivityUser>> getActivityUserByUserId(long userId);

    /**
     * 通过id查询活动
     * @param activityId
     * @return
     */
    Response<List<ActivityUser>> getActivityUserByActivityId(long activityId);


    /**删除活动
     * @param activityId
     * @return成功返回true，失败返回false
     */
    Response<Boolean> deleteActivityUserByActivityId(long activityId);

    /**删除活动
     * @param userId
     * @return成功返回true，失败返回false
     */
    Response<Boolean> deleteActivityUserByUserId(long userId);


}
