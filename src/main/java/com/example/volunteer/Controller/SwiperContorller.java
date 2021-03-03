package com.example.volunteer.Controller;

import com.aliyun.oss.model.CannedAccessControlList;
import com.example.volunteer.Entity.Swiper;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.SwiperService;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.utils.OSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "轮播图Controller")
@RestController
@RequestMapping("swiper")
public class SwiperContorller extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(SwiperContorller.class);

    @Autowired
    private SwiperService swiperService;

    @Autowired
    private OSSUtil ossUtil;

    @GetMapping("/getSwiperByNewsId")
    @ApiOperation("获得轮播图by newsId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "轮播图对应新闻id", paramType = "query", dataType = "long"),
    })
    public Response<List<Swiper>> getSwiperByNewsId(@RequestParam("newsId") long newsId) {
        Response<List<Swiper>> response = new Response<>();
        try {
            return swiperService.getSwiperByNewsId(newsId);
        } catch (IllegalArgumentException e) {
            logger.warn("[getSwiperByNewsId Illegal Argument], newsId: {}", newsId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getSwiperByNewsId Runtime Exception], newsId: {}", newsId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getSwiperByNewsId Exception], newsId: {}", newsId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }
    @GetMapping("/getSwiperByPriority")
    @ApiOperation("获得轮播图by priority")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "priority", value = "轮播图优先级", paramType = "query", dataType = "string"),
    })
    public Response<List<Swiper>> getSwiperByPriority(@RequestParam("priority") String priority) {
        Response<List<Swiper>> response = new Response<>();
        try {
            return swiperService.getSwiperByPriority(priority);
        } catch (IllegalArgumentException e) {
            logger.warn("[getSwiperByPriority Illegal Argument], priority: {}", priority, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getSwiperByPriority Runtime Exception], priority: {}", priority, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getSwiperByPriority Exception], priority: {}", priority, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }


    @PostMapping("/addSwiper")
    @ApiOperation("添加轮播图")
    public Response<Boolean> addSwiper(@RequestBody Swiper swiper,@RequestParam("file") MultipartFile uploadFile) {
        Response<Boolean> response = new Response<>();
        try {
            return swiperService.addSwiper(swiper,uploadFile);
        } catch (IllegalArgumentException e) {
            logger.warn("[addSwiper Illegal Argument], swiper: {}", swiper, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addSwiper Runtime Exception], swiper: {}", swiper, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addSwiper Exception], swiper: {}", swiper, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/addSwiperTest")
    @ApiOperation("添加轮播图")
    public Response<Boolean> addSwiperTest(@RequestParam("file") MultipartFile uploadFile) {
        Response<Boolean> response = new Response<>();
        try {
            String url = ossUtil.uploadFile("our-volunteer",uploadFile);
            response.setSuc(StringUtils.isBlank(url));
            return response;
        } catch (IllegalArgumentException e) {
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/updateSwiperPriority")
    @ApiOperation("更新轮播图优先级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newSwiperPriority", value = "轮播图优先级", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "swiperId", value = "轮播图id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateCommentLikeNumber(@RequestParam("newSwiperPriority")String newSwiperPriority,@RequestParam("swiperId") long swiperId) {
        Response<Boolean> response = new Response<>();
        try {
            return swiperService.updateSwiperPriority(newSwiperPriority,swiperId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateSwiperPriority Illegal Argument], : swiperId {}", swiperId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateSwiperPriority Runtime Exception], : swiperId {}", swiperId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateSwiperPriority Exception], :swiperId {}", swiperId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }

    @PostMapping("/updateSwiperText")
    @ApiOperation("更新轮播图文本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newSwiperText", value = "轮播图文本", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "swiperId", value = "轮播图id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> updateSwiperText(@RequestParam("newSwiperText")String newSwiperText,@RequestParam("swiperId") long swiperId) {
        Response<Boolean> response = new Response<>();
        try {
            return swiperService.updateSwiperText(newSwiperText,swiperId);
        } catch (IllegalArgumentException e) {
            logger.warn("[updateSwiperText Illegal Argument], : swiperId {}", swiperId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateSwiperText Runtime Exception], : swiperId {}", swiperId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateSwiperText Exception], :swiperId {}", swiperId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }



    @PostMapping("/deleteSwiperById")
    @ApiOperation("删除轮播图By swiperId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "swiperId", value = "评论id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteSwiperById(@RequestParam("swiperId") long swiperId) {
        Response<Boolean> response = new Response<>();
        try {
            return swiperService.deleteSwiperById(swiperId);
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteSwiperById Illegal Argument], swiperId: {}", swiperId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteSwiperById Runtime Exception], swiperId: {}", swiperId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteSwiperById Exception], swiperId: {}", swiperId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
    }
}
