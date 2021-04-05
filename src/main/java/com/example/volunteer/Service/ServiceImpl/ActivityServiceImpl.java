package com.example.volunteer.Service.ServiceImpl;

import com.example.volunteer.Dao.ActivityDao;
import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Dao.ActivityPictureDao;
import com.example.volunteer.Dao.ActivitySignFileModelDao;
import com.example.volunteer.Entity.*;
import com.example.volunteer.Request.ActivityRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.enums.ResponseEnum;

import com.example.volunteer.Service.ActivityService;
import com.example.volunteer.utils.OSSUtil;
import com.example.volunteer.utils.SerialUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private ActivityPictureDao activityPictureDao;

    @Autowired
    private ActivitySignFileModelDao activitySignFileModelDao;

    @Autowired
    private OSSUtil ossUtil;

    @Override
    public Response<Boolean> addActivity(ActivityRequest activityRequest){
        Response<Boolean> response = new Response<>();

        for(Activity activity:activityRequest.getActivityList()) {
            boolean result = activityDao.insertActivity(activity) > 0;
            if (!result) {
                logger.error("[addActivity Fail], request: {}", SerialUtil.toJsonStr(activityRequest));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        response.setSuc(true);
        return response;
    }

    @Override
    public Response<Boolean> updateActivity(Activity activity) {
        Response<Boolean> response=new Response<>();
        boolean result = activityDao.updateActivity(activity) > 0;
        if(!result){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
        }
        else{
            response.setSuc(true);
        }
        return response;
    }

    @Override
    public Response<ActivityDTO> getActivityByActivityId(long activityId) {
        Response<ActivityDTO> response=new Response<>();

        ActivityDTO activityDTO = activityDao.getActivityByActivityId(activityId);
        if (activityDTO == null) {
            response.setFail(ResponseEnum.FAIL);
        }
        else {
            activityDTO.setActivitySignFileModelList(activitySignFileModelDao.getActivitySignFileModelByActivityId(activityId));
            response.setSuc(activityDTO);
        }
        return response;
    }

    @Override
    public Response<List<ActivityDTO>> getActivityByOrganizerId(long organizerId) {
        Response<List<ActivityDTO>> response=new Response<>();

        List<ActivityDTO> activityDTOList = activityDao.getActivityByOrganizer(organizerId);
        if (activityDTOList == null) {
            response.setFail(ResponseEnum.FAIL);
        }
        else {
            response.setSuc(activityDTOList);
        }
        return response;
    }

    @Override
    public Response<Boolean> deleteActivityByActivityId(long activityId){
        Response<Boolean> response=new Response<>();

        ActivityDTO activityDTO=activityDao.getActivityByActivityId(activityId);
        if (activityDTO == null) {
            response.setFail(ResponseEnum.FAIL);
        }
        else {
            boolean result=activityDao.deleteActivityByActivityId(activityId) > 0;
            if(!result){
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
            }
            else {
                response.setSuc(true);
            }
        }
        return response;
    }

    @Override
    public Response<List<ActivitySignFileModel>> getSignFileModel(long activityId) {
        Response<List<ActivitySignFileModel>> response=new Response<>();
        List<ActivitySignFileModel> activitySignFileModelList;

        activitySignFileModelList = activitySignFileModelDao.getActivitySignFileModelByActivityId(activityId);
        if(CollectionUtils.isEmpty(activitySignFileModelList)){
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
            return response;
        }
        else{
            response.setSuc(activitySignFileModelList);
        }
        return response;
    }

    @Override
    public Response<Boolean> addActivitySignFileModel(long activityId, MultipartFile[] signFileModel){
        Response<Boolean> response=new Response<>();

        boolean result;
        String bucketName = "sign-file-model";
        String filename = "activity_"+activityId+"/";
        for(MultipartFile file : signFileModel) {
            String url = ossUtil.uploadFile(bucketName, file, filename+file.getOriginalFilename());
            if (StringUtils.isBlank(url)) {
                logger.error("[addActivitySignFileModel Fail], file: {}", SerialUtil.toJsonStr(file.getOriginalFilename()));
                response.setFail(ResponseEnum.UPLOAD_OSS_FAILURE);
                return response;
            }
            ActivitySignFileModel activitySignFileModel = new ActivitySignFileModel();
            activitySignFileModel.setActivityId(activityId);
            activitySignFileModel.setFileModelName(file.getOriginalFilename());
            activitySignFileModel.setFileModelUrl(url);
            result=activitySignFileModelDao.addActivitySignFileModel(activitySignFileModel) > 0;
            if(!result){
                logger.error("[addActivitySignFileModel Fail], signFileModel: {}", SerialUtil.toJsonStr(signFileModel));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }

        result = activityDao.updateIsActivitySignFileModelByActivityId(activityId,true) > 0;
        if(!result){
            logger.error("[updateIsActivitySignFileModelByActivityId Fail], activityId: {}", activityId);
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
            return response;
        }
        response.setSuc(true);
        return response;
    }
    @Override
    public Response<Boolean> addActivityPicture(long activityId, MultipartFile[] activityPicture) {
        Response<Boolean> response=new Response<>();

        boolean result;
        String bucketName = "activity-picture-file-model";
        String filename = "activity_picture"+activityId+"/";
        for(MultipartFile file : activityPicture) {
            String url = ossUtil.uploadFile(bucketName, file, filename+file.getOriginalFilename());
            if (StringUtils.isBlank(url)) {
                logger.error("[addActivitySignFileModel Fail], file: {}", SerialUtil.toJsonStr(file.getOriginalFilename()));
                response.setFail(ResponseEnum.UPLOAD_OSS_FAILURE);
                return response;
            }
            ActivityPicture Pic = new ActivityPicture();
            Pic.setActivityId(activityId);
            //Pic.setf(file.getOriginalFilename());
            Pic.setPictureUrl(url);
            result= activityPictureDao.addActivityPicture(Pic) > 0;
            if(!result){
                logger.error("[addCommentPicture Fail], Pic: {}", SerialUtil.toJsonStr(Pic));
                response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
                return response;
            }
        }
        result = activityDao.updateIsActivityPictureByActivityId(activityId,true) > 0;
        if(!result){
            logger.error("[updateIsActivityPictureByActivityId Fail], activityId: {}", activityId);
            response.setFail(ResponseEnum.OPERATE_DATABASE_FAIL);
            return response;
        }

        response.setSuc(true);
        return response;
    }
}
