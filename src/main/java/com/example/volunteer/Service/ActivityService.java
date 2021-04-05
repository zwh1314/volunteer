package com.example.volunteer.Service;

import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Entity.Activity;
import com.example.volunteer.Entity.ActivitySignFileModel;
import com.example.volunteer.Request.ActivityRequest;
import com.example.volunteer.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ActivityService {

    /**增加活动
     * @param activityRequest
     * @return 成功返回true，失败返回false
     */
    Response<Boolean> addActivity(ActivityRequest activityRequest);

    /**更新活动
     * @param activity
     * @return 成功返回true，失败返回false
     */
    Response<Boolean> updateActivity(Activity activity);


    /**
     * 通过id查询活动
     * @param activityId
     * @return 查询结果链表
     */
    public Response<ActivityDTO> getActivityByActivityId(long activityId);

    /**
     * 通过organizerId查询活动
     * @param organizerId
     * @return 查询结果链表
     */
    public Response<List<ActivityDTO>> getActivityByOrganizerId(long organizerId);


    /**删除活动
     * @param activityId
     * @return 成功返回true，失败返回false
     */
    Response<Boolean> deleteActivityByActivityId(long activityId);

    /**下载活动报名表模板
     * @param activityId
     * @return 查询结果链表
     */
    Response<List<ActivitySignFileModel>> getSignFileModel(long activityId);

    /**上传活动报名表
     * @param activityId
     * @param signFileModel
     * @return 成功返回true，失败返回false
     */
    Response<Boolean> addActivitySignFileModel(long activityId, MultipartFile[] signFileModel);

    Response<Boolean> addActivityPicture(long activityId, MultipartFile[] activityPicture);

    Response<List<ActivityDTO>> getActivityByNumber(long number);
}
