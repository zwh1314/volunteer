package com.example.volunteer.Controller;

import com.example.volunteer.Entity.ActivityNews;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.ActivityNewsRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.ActivityNewsService;
import com.example.volunteer.enums.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "活动新闻Controller")
@RestController
@RequestMapping("activityNews")
public class ActivityNewsController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ActivityNewsController.class);

    @Autowired
    private ActivityNewsService activityNewsService;


    @GetMapping("/getActivityNewsByActivityId")
    @ApiOperation("获得活动 Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", paramType = "query", dataType = "long"),
    })
    public Response<List<ActivityNews>> getActivityNewsById(@RequestParam("activityId") long activityId) {
        Response<List<ActivityNews>> response = new Response<>();
        try {
            return activityNewsService.getActivityNewsByActivityId(activityId);
        } catch (IllegalArgumentException e) {
            logger.warn("[getActivityNewsByActivityId Illegal Argument], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getActivityNewsByActivityId Runtime Exception], activityId: {}", activityId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getActivityNewsByActivityId Exception], activityId: {}", activityId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }


    @PostMapping("/addActivityNews")
    @ApiOperation("添加活动新闻")
    public Response<Boolean> addActivityNews(@RequestBody ActivityNewsRequest activityNewsRequest) {
        Response<Boolean> response = new Response<>();
        try {
            return activityNewsService.addActivityNews(activityNewsRequest);
        } catch (IllegalArgumentException e) {
            logger.warn("[addActivityNews Illegal Argument], activityNewsRequest: {}", activityNewsRequest, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addActivityNews Runtime Exception], activityNewsRequest: {}", activityNewsRequest, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addActivityNews Exception], activityNewsRequest: {}", activityNewsRequest, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/updateActivityNewsContentByNewsId")
    @ApiOperation("更新活动新闻By newsId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "活动新闻id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateActivityNewsContentByNewsId(@RequestParam("activityNewsContent")String activityNewsContent,@RequestParam("newsId") long newsId) {
        Response<Boolean> response = new Response<>();
        try {
            return activityNewsService.updateActivityNewsContent(activityNewsContent,newsId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateActivityNewsContentByNewsId Illegal Argument], : newsId {}", newsId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateActivityNewsContentByNewsId Runtime Exception], : newsId {}", newsId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateActivityNewsContentByNewsId Exception], :newsId {}", newsId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/updateActivityNewsTitleByNewsId")
    @ApiOperation("更新活动标题By newsId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "活动新闻id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateActivityTitleContentByNewsId(@RequestParam("activityNewsTitle")String activityNewsTitle,@RequestParam("newsId") long newsId) {
        Response<Boolean> response = new Response<>();
        try {
            return activityNewsService.updateActivityNewsTitle(activityNewsTitle,newsId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateActivityNewsTitleByNewsId Illegal Argument], : newsId {}", newsId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateActivityNewsTitleByNewsId Runtime Exception], : newsId {}", newsId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateActivityNewsTitleByNewsId Exception], :newsId {}", newsId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/updateActivityNewsPictureByNewsId")
    @ApiOperation("更新活动图片By newsId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "活动新闻id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateActivityPictureContentByNewsId(@RequestParam("activityNewsPicture")String activityNewsPicture,@RequestParam("newsId") long newsId) {
        Response<Boolean> response = new Response<>();
        try {
            return activityNewsService.updateActivityNewsPicture(activityNewsPicture,newsId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateActivityNewsPictureByNewsId Illegal Argument], : newsId {}", newsId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateActivityNewsPictureByNewsId Runtime Exception], : newsId {}", newsId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateActivityNewsPictureByNewsId Exception], :newsId {}", newsId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/deleteActivityNewsByNewsId")
    @ApiOperation("删除活动新闻By newsId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "活动新闻id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteActivityNewsByNewsId(@RequestParam("newsId") long newsId) {
        Response<Boolean> response = new Response<>();
        try {
            return activityNewsService.deleteActivityNewsById(newsId);
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteActivityNewsByNewsId Illegal Argument], newsId: {}", newsId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteActivityNewsByNewsId Runtime Exception], newsId: {}", newsId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteActivityNewsByNewsId Exception], newsId: {}", newsId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }
}
