package com.example.volunteer.Service;

import com.example.volunteer.Entity.ActivityUser;

import java.util.Date;
import java.util.List;

public interface  ActivityUserService {
    /**增加活动
     * @param activityUser
     * @return成功返回true，失败返回false
     */
    boolean addActivityUser(ActivityUser activityUser);

    /**更新报名表状态
     * @param userId，formStatus
     * @return成功返回true，失败返回false
     */
    boolean updateFormStatusByUserId(String formStatus,long userId);

    /**更新交表日期
     * @param userId，formDate
     * @return成功返回true，失败返回false
     */
    boolean updateFormDateByUserId(Date formDate, long userId);
    /**
     * 通过id查询活动
     * @param userId
     * @return
     */
    List<ActivityUser> getActivityUserByUserId(long userId);

    /**
     * 通过id查询活动
     * @param activityId
     * @return
     */
    List<ActivityUser> getActivityUserByActivityId(long activityId);


    /**删除活动
     * @param activityId
     * @return成功返回true，失败返回false
     */
    boolean deleteActivityUserByActivityId(long activityId);

    /**删除活动
     * @param userId
     * @return成功返回true，失败返回false
     */
    boolean deleteActivityUserByUserId(long userId);


}
