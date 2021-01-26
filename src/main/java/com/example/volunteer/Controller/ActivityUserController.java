package com.example.volunteer.Controller;

import com.example.volunteer.Entity.ActivityUser;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.ActivityUserService;
import com.example.volunteer.enums.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Api(tags = "活动用户Controller")
@RestController
@RequestMapping("activityUser")
public class ActivityUserController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ActivityUserController.class);

    @Autowired
    private ActivityUserService activityUserService;


    @GetMapping("/getActivityUserByActivityId")
    @ApiOperation("获得活动用户by 活动Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", paramType = "query", dataType = "long"),
    })
    public Response<List<ActivityUser>> getActivityUserByActivityId(@RequestParam("activityId") long activityId) {
        Response<List<ActivityUser>> response = new Response<>();
        try {
            response.setSuc(activityUserService.getActivityUserByActivityId(activityId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getActivityUserByActivityId Illegal Argument], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getActivityUserByActivityId Runtime Exception], activityId: {}", activityId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getActivityUserByActivityId Exception], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }
    @GetMapping("/getActivityUserByUserId")
    @ApiOperation("获得活动用户by 用户Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "long"),
    })
    public Response<List<ActivityUser>> getActivityUserByUserId(@RequestParam("userId") long userId) {
        Response<List<ActivityUser>> response = new Response<>();
        try {
            response.setSuc(activityUserService.getActivityUserByUserId(userId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getActivityUserByUserId Illegal Argument], userId: {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getActivityUserByUserId Runtime Exception], userId: {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getActivityUserByUserId Exception], userId: {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/addActivityUser")
    @ApiOperation("添加活动用户")
    public Response<Boolean> addActivityUser(@RequestParam("ActivityUser")ActivityUser activityUser) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityUserService.addActivityUser(activityUser));
        } catch (IllegalArgumentException e) {
            logger.warn("[addActivityUser Illegal Argument], activityUser: {}", activityUser, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addActivityUser Runtime Exception], activityUser: {}", activityUser, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addActivityUser Exception], activityUser: {}", activityUser, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/updateFormStatusByUserId")
    @ApiOperation("更新活动报名表状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateFormStatusByUserId(@RequestParam("userId") long userId,@RequestParam("formStatus") String formStatus) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityUserService.updateFormStatusByUserId(formStatus,userId));
        } catch (IllegalArgumentException e) {
            logger.warn("[updateFormStatusByUserId Illegal Argument], : userId {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateFormStatusByUserId Runtime Exception], :userId {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateFormStatusByUserId Exception], :userId {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/updateFormDateByUserId")
    @ApiOperation("更新交表日期")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateFormDateByUserId(@RequestParam("userId") long userId,@RequestParam("formDate") Date formDate) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityUserService.updateFormDateByUserId(formDate,userId));
        } catch (IllegalArgumentException e) {
            logger.warn("[updateFormDateByUserId Illegal Argument], : userId {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateFormDateByUserId Runtime Exception], :userId {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateFormDateByUserId Exception], :userId {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


    @PostMapping("/deleteActivityUserByUserId")
    @ApiOperation("删除活动用户userId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityUserId", value = "活动用户id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteActivityUserByUserId(@RequestParam("userId") long userId) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(activityUserService.deleteActivityUserByUserId(userId));
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteActivityUserByUserId Illegal Argument], userId: {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteActivityUserByUserId Runtime Exception], userId: {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteActivityUserByUserId Exception], userId: {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


}