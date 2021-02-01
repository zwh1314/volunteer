package com.example.volunteer.Controller;

import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Exception.VolunteerRuntimeException;
import com.example.volunteer.Request.UserInfoRequest;
import com.example.volunteer.Response.Response;
import com.example.volunteer.enums.ResponseEnum;
import com.example.volunteer.Service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户信息Controller")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserInfoByUserId")
    @ApiOperation("获得用户信息By userId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "账户id", paramType = "query", dataType = "long"),
    })
    public Response<UserInfoDTO> getUserInfoByUserId(@RequestParam("userId") long userId) {
        Response<UserInfoDTO> response = new Response<>();
        try {
            validateUserId(userId);
            response.setSuc(userInfoService.getUserInfoByUserId(userId));
        } catch (IllegalArgumentException e) {
            logger.warn("[getUserInfoByUserId Illegal Argument], userId: {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getUserInfoByUserId Runtime Exception], userId: {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getUserInfoByUserId Exception], userId: {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/addUserInfo")
    @ApiOperation("添加用户信息")
    public Response<Boolean> addUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        Response<Boolean> response = new Response<>();
        try {
            validateUserInfoRequest(userInfoRequest);
            response.setSuc(userInfoService.addUserInfo(userInfoRequest));
        } catch (IllegalArgumentException e) {
            logger.warn("[addUserInfo Illegal Argument], userInfoRequest: {}", userInfoRequest, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[addUserInfo Runtime Exception], userInfoRequest: {}", userInfoRequest, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[addUserInfo Exception], userInfoRequest: {}", userInfoRequest, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/updateUserInfoByUserId")
    @ApiOperation("更新用户信息By userId")
    public Response<Boolean> updateUserInfoByUserId(@RequestBody UserInfoRequest userInfoRequest) {
        Response<Boolean> response = new Response<>();
        try {
            validateUserInfoRequest(userInfoRequest);

            response.setSuc(userInfoService.updateUserInfo(userInfoRequest));
        } catch (IllegalArgumentException e) {
            logger.warn("[updateUserInfoByUserId Illegal Argument], userInfoRequest: {}", userInfoRequest, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[updateUserInfoByUserId Runtime Exception], userInfoRequest: {}", userInfoRequest, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[updateUserInfoByUserId Exception], userInfoRequest: {}", userInfoRequest, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    @PostMapping("/deleteUserInfoByUserId")
    @ApiOperation("删除用户信息By userId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "账户id", paramType = "query", dataType = "long"),
    })
    public Response<Boolean> deleteUserInfoByUserId(@RequestParam("userId") long userId) {
        Response<Boolean> response = new Response<>();
        try {
            validateUserId(userId);
            response.setSuc(userInfoService.deleteUserInfoByUserId(userId));
        } catch (IllegalArgumentException e) {
            logger.warn("[deleteUserInfoByUserId Illegal Argument], userId: {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[deleteUserInfoByUserId Runtime Exception], userId: {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[deleteUserInfoByUserId Exception], userId: {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }

        return response;
    }

    private void validateUserInfoRequest(UserInfoRequest request) {
        Validate.notNull(request);
        Validate.isTrue(CollectionUtils.isNotEmpty(request.getUserInfoList()));
    }

    @GetMapping("/getCreditsByUserId")
    @ApiOperation("查询用户积分By UserId")
    public Response<Integer> selectCreditsByUserId(@RequestParam("userId") long userId){
        Response<Integer> response = new Response<>();
        try{
            validateUserId(userId);
            response.setSuc(userInfoService.getCreditsByUserId(userId));
        }catch (IllegalArgumentException e) {
            logger.warn("[getCreditsByUserId Illegal Argument], userId: {}", userId, e);
            response.setFail(ResponseEnum.ILLEGAL_PARAM);
            return response;
        } catch (VolunteerRuntimeException e) {
            logger.error("[getCreditsByUserId Runtime Exception], userId: {}", userId, e);
            response.setFail(e.getExceptionCode(), e.getMessage());
            return response;
        }  catch (Exception e) {
            logger.error("[getCreditsByUserId Exception], userId: {}", userId, e);
            response.setFail(ResponseEnum.SERVER_ERROR);
            return response;
        }
        return response;
    }
}
