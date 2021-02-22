package com.example.volunteer.Controller;


import com.example.volunteer.Entity.Video;
import com.example.volunteer.Exception.VolunteerRuntimeException;

import com.example.volunteer.RedisService.VideoRedisService;
import com.example.volunteer.Request.VideoRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.VideoService;
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

@Api(tags = "视频Controller")
@RestController
@RequestMapping("video")
public class VideoController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRedisService videoRedisService;

    @GetMapping("/getVideoByPublisherId")
    @ApiOperation("获得视频by发布者Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "publisherId", value = "视频发布者", paramType = "query", dataType = "long"),
    })
    public Response<List<Video>> getVideoByPublisherId(@RequestParam("publisherId") long publisherId) {
        Response<List<Video>> response = new Response<>();
        try {
            return videoService.getVideoByPublisherId(publisherId);
        } catch (IllegalArgumentException e) {
            logger.warn("[getVideoByPublisherId Illegal Argument], publisherId: {}", publisherId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoByPublisherId Runtime Exception], publisherId: {}", publisherId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoByPublisherId Exception], publisherId: {}", publisherId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @GetMapping("/getVideoByRelativeText")
    @ApiOperation("获得视频by RelativeText")

    public Response<List<Video>> getVideoByRelativeText(@RequestParam("relativeText") String relativeText) {
        Response<List<Video>> response = new Response<>();
        try {
            return videoService.getVideoByRelativeText(relativeText);
        } catch (IllegalArgumentException e) {
            logger.warn("[getVideoByRelativeText Illegal Argument], relativeText: {}", relativeText, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoByRelativeText Runtime Exception], relativeText: {}", relativeText, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoByRelativeText Exception], relativeText: {}", relativeText, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }


    @GetMapping("/getVideoInOneWeek")
    @ApiOperation("获得一周视频")
    public Response<List<Video>> getVideoInOneWeek() {
        Response<List<Video>> response = new Response<>();
        try {
            return videoService.getVideoInOneWeek();
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoInOneWeek Runtime Exception]",  e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoInOneWeek Exception]", e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }


    @PostMapping("/addVideo")
    @ApiOperation("添加视频")
    public Response<Boolean> addVideo(@RequestBody VideoRequest videoRequest) {
        Response<Boolean> response = new Response<>();
        try {
            return videoService.addVideo(videoRequest);
        } catch (IllegalArgumentException e) {
            logger.warn("[addVideo Illegal Argument], videoRequest: {}", videoRequest, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addVideo Runtime Exception], videoRequest: {}", videoRequest, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addVideo Exception], videoRequest: {}", videoRequest, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/updateVideoLikeNumber")
    @ApiOperation("更新视频获赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "视频id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateVideoLikeNumber(@RequestParam("likeNumber")long likeNumber,@RequestParam("videoId") long videoId) {
        Response<Boolean> response = new Response<>();
        try {
            return videoService.updateVideoLikeNumber(likeNumber, videoId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateVideoLikeNumber Illegal Argument], : videoId {}", videoId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateVideoLikeNumber Runtime Exception], : videoId {}", videoId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[updateVideoLikeNumber Exception], :videoId {}", videoId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }


    @PostMapping("/updateVideoTextContent")
    @ApiOperation("更新视频文本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "视频id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateCommentText(@RequestParam("textContent")String textContent,@RequestParam("videoId") long videoId) {
        Response<Boolean> response = new Response<>();
        try {
            return videoService.updateVideoTextContent(textContent, videoId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateVideoTextContent Illegal Argument], : videoId {}", videoId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateVideoTextContent Runtime Exception], : videoId {}", videoId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[updateVideoTextContent Exception], :videoId {}", videoId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }



    @PostMapping("/deleteVideoById")
    @ApiOperation("删除视频By VideoById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "视频id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteVideoById(@RequestParam("videoId") long videoId) {
        Response<Boolean> response = new Response<>();
        try {
            return videoService.deleteVideoById(videoId);
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteVideoById Illegal Argument], videoId: {}", videoId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteVideoById Runtime Exception], videoId: {}", videoId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteVideoById Exception], commentId: {}", videoId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @GetMapping("/getVideoLikeByVideoId")
    @ApiOperation("通过videoId查询点赞")
    public Response<Long> getVideoLikeByVideoId(@RequestParam("videoId") long videoId) {
        Response<Long> response = new Response<>();
        try {
           return  videoRedisService.getVideoLikeByVideoId(videoId);
        } catch (IllegalArgumentException e) {
            logger.warn("[getVideoLikeByVideoId Illegal Argument], videoId: {}", videoId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoLikeByVideoId Runtime Exception],videoId: {}",videoId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("getVideoLikeByVideoId Exception], videoId: {}",videoId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

    }

    @PostMapping("likesVideo")
    @ApiOperation("点赞视频")
    public Response<Boolean> likesVideo(@RequestParam("videoId") long videoId){
        Response<Boolean> response = new Response<>();
        try {
          return  videoRedisService.likesVideo(videoId);
        } catch (IllegalArgumentException e) {
            logger.warn("[likesVideo Illegal Argument], : videoId {}", videoId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[likesVideo Runtime Exception], : videoId {}", videoId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[likesVideo Exception], :videoId {}", videoId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

    }
}
