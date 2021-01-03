package com.example.volunteer.Controller;

import com.example.volunteer.DTO.ActivityDTO;
import com.example.volunteer.Entity.Activity;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.ActivityService;
import com.example.volunteer.enums.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api(tags = "活动Controller")
@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;


    @PostMapping("/getActivityByActivityId")
    @ApiOperation("获得活动 by活动Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", paramType = "query", dataType = "long"),
    })
    public Response<ActivityDTO> getActivityByActivityId(@RequestParam("activityId") long activityId) {
        Response<ActivityDTO> response = new Response<>();
        try {
            response.setSuc(activityService.getActivityByActivityId(activityId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getActivityByActivityId Illegal Argument], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getActivityByActivityId Runtime Exception], activityId: {}", activityId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getActivityByActivityId Exception], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


    @PostMapping("/addActivity")
    @ApiOperation("添加活动")
    public Response<Boolean> addActivityNews(@RequestParam("activity")Activity activity) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityService.addActivity(activity));
        } catch (IllegalArgumentException e) {
            logger.warn("[addActivity Illegal Argument], activity: {}", activity, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addActivity Runtime Exception], activity: {}", activity, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addActivity Exception], activity: {}", activity, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/updateActivity")
    @ApiOperation("更新活动")
    public Response<Boolean> updateActivity(@RequestParam("activity") Activity activity) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityService.updateActivity(activity));
        } catch (IllegalArgumentException e) {
            logger.warn("[updateActivity Illegal Argument], : activity {}", activity, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateActivity Runtime Exception], :activity {}", activity, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateActivity Exception], :activity {}", activity, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


    @PostMapping("/deleteActivityNewsByNewsId")
    @ApiOperation("删除活动By activityId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteActivityNewsByNewsId(@RequestParam("activityId") long activityId) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityService.deleteActivityByActivityId(activityId));
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteActivityNewsByNewsId Illegal Argument], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteActivityNewsByNewsId Runtime Exception], activityId: {}", activityId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteActivityNewsByNewsId Exception], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


}
