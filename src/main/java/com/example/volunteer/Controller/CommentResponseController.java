package com.example.volunteer.Controller;


import com.example.volunteer.Entity.CommentResponse;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.CommentResponseRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.CommentResponseService;
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

@Api(tags = "评论回复Controller")
@RestController
@RequestMapping("commentResponse")
public class CommentResponseController {
    private static final Logger logger = LoggerFactory.getLogger(CommentResponseController.class);

    @Autowired
    private CommentResponseService responseService;

    @GetMapping("/getCommentResponseByCommentId")
    @ApiOperation("获得评论Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "评论id", paramType = "query", dataType = "long"),
    })
    public Response<List<CommentResponse>> getCommentResponseByCommentId(@RequestParam("commentId") long commentId) {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getCommentResponseByCommentId(commentId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getCommentResponseByCommentId Illegal Argument], commentId: {}", commentId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getCommentResponseByCommentId Runtime Exception], commentId: {}", commentId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getCommentResponseByCommentId Exception], commentId: {}", commentId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


    @GetMapping("/getVideoCommentResponseByCommentId")
    @ApiOperation("获得视频评论回复 by CommentId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentId", value = "评论id", paramType = "query", dataType = "long"),
    })
    public Response<List<CommentResponse>> getVideoCommentResponseByCommentId(@RequestParam("commentId") long commentId) {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getVideoCommentResponseByCommentId(commentId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getVideoCommentResponseByCommentId Illegal Argument], commentId: {}", commentId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoCommentResponseByCommentId Runtime Exception], commentId: {}", commentId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoCommentResponseByCommentId Exception], commentId: {}", commentId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @GetMapping("/getCommentResponseInOneWeek")
    @ApiOperation("获得一周评论回复")
    public Response<List<CommentResponse>> getCommentResponseInOneWeek() {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getCommentResponseInOneWeek());
        } catch (VolunteerRuntimeException e) {
            logger.error("[getCommentResponseInOneWeek Runtime Exception]",  e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getCommentResponseInOneWeek Exception]", e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @GetMapping("/getVideoCommentResponseInOneWeek")
    @ApiOperation("获得一周视频回复")
    public Response<List<CommentResponse>> getVideoCommentResponseInOneWeek() {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getVideoCommentResponseInOneWeek());
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoCommentResponseInOneWeek Runtime Exception]",  e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoCommentResponseInOneWeek Exception]", e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }


    @GetMapping("/getCommentResponseByRelativeText")
    @ApiOperation("获得相似评论by relativeText")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "relativeText", value = "相似文本", paramType = "query", dataType = "string"),
    })
    public Response<List<CommentResponse>> getCommentResponseByRelativeText(@RequestParam("relativeText") String relativeText) {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getCommentResponseByRelativeText(relativeText));
        } catch (IllegalArgumentException e) {
            logger.warn("[getCommentResponseByRelativeText Illegal Argument], relativeText: {}", relativeText, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getCommentResponseByRelativeText Runtime Exception], relativeText: {}", relativeText, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getCommentResponseByRelativeText Exception], relativeText: {}", relativeText, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }
    @GetMapping("/getVideoCommentResponseByRelativeText")
    @ApiOperation("获得相似视频评论回复by relativeText")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "relativeText", value = "相似文本", paramType = "query", dataType = "string"),
    })
    public Response<List<CommentResponse>> getVideoCommentResponseByRelativeText(@RequestParam("relativeText") String relativeText) {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getVideoCommentResponseByRelativeText(relativeText));
        } catch (IllegalArgumentException e) {
            logger.warn("[getVideoCommentResponseByRelativeText Illegal Argument], relativeText: {}", relativeText, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoCommentResponseByRelativeText Runtime Exception], relativeText: {}", relativeText, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoCommentResponseByRelativeText Exception], relativeText: {}", relativeText, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @GetMapping("/getCommentResponseByPublisherId")
    @ApiOperation("获得评论回复 by PublisherId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "publisherId", value = "评论者", paramType = "query", dataType = "long"),
    })
    public Response<List<CommentResponse>> getCommentResponseByPublisherId(@RequestParam("publisherId") long publisherId) {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getCommentResponseByPublisherId(publisherId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getCommentResponseByPublisherId Illegal Argument], commentId: {}", publisherId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getCommentResponseByPublisherId Runtime Exception], commentId: {}", publisherId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getCommentResponseByPublisherId Exception], commentId: {}", publisherId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }
    @GetMapping("/getVideoCommentResponseByPublisherId")
    @ApiOperation("获得视频评论回复 by PublisherId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "publisherId", value = "评论者", paramType = "query", dataType = "long"),
    })
    public Response<List<CommentResponse>> getVideoCommentResponseByPublisherId(@RequestParam("publisherId") long publisherId) {
        Response<List<CommentResponse>> response = new Response<>();
        try {
            response.setSuc(responseService.getVideoCommentResponseByPublisherId(publisherId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getVideoCommentResponseByPublisherId Illegal Argument], commentId: {}", publisherId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getVideoCommentResponseByPublisherId Runtime Exception], commentId: {}", publisherId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getVideoCommentResponseByPublisherId Exception], commentId: {}", publisherId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/addCommentResponse")
    @ApiOperation("添加回复")
    public Response<Boolean> addComment(@RequestBody CommentResponseRequest commentResponseRequest) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(responseService.addCommentResponse(commentResponseRequest));
        } catch (IllegalArgumentException e) {
            logger.warn("[addCommentResponse( Illegal Argument], commentResponseRequest: {}", commentResponseRequest, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addCommentResponse( Runtime Exception], commentResponseRequest: {}", commentResponseRequest, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addCommentResponse( Exception], commentResponseRequest: {}", commentResponseRequest, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/updateResponseLikeNumber")
    @ApiOperation("更新回复赞数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "responseId", value = "评论回复id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean>updateResponseLikeNumber(@RequestParam("responseLikeNumber")long responseLikeNumber,@RequestParam("responseId") long responseId) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(responseService.updateResponseLikeNumber(responseLikeNumber,responseId));
        } catch (IllegalArgumentException e) {
            logger.warn("[updateResponseLikeNumberr Illegal Argument], : responseId {}", responseId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateResponseLikeNumber Runtime Exception], : responseId {}", responseId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateResponseLikeNumber Exception], :responseId {}", responseId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/updateResponseText")
    @ApiOperation("更新评论回复文本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "responseId", value = "评论回复id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateCommentText(@RequestParam("responseText")String responseText,@RequestParam("responseId") long responseId) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(responseService.updateResponseText(responseText, responseId));
        } catch (IllegalArgumentException e) {
            logger.warn("[updateResponseText Illegal Argument], : responseId {}", responseId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateResponseText Runtime Exception], : responseId {}", responseId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error("[updateResponseText Exception], :responseId {}", responseId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }



    @PostMapping("/deleteCommentResponseById")
    @ApiOperation("删除评论回复By responseId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "responseId", value = "评论回复id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteActivityNewsByNewsId(@RequestParam("responseId") long responseId) {
        Response<Boolean> response = new Response<>();
        try {
            response.setSuc(responseService.deleteCommentResponseById(responseId));
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteCommentResponseById Illegal Argument], responseId: {}", responseId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteCommentResponseById Runtime Exception], responseId: {}", responseId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteCommentResponseById Exception], responseId: {}", responseId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

}
