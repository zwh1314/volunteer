package com.example.volunteer.Service;

import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Request.UserInfoRequest;
import com.example.volunteer.Response.Response;

public interface UserInfoService {
    Response<UserInfoDTO> getUserInfoByUserId(long userId);

    Response<Boolean> addUserInfo(UserInfoRequest userInfoRequest);

    Response<Boolean> updateUserInfo(UserInfoRequest userInfoRequest);

    Response<Boolean> deleteUserInfoByUserId(long userId);

    Response<Integer> getCreditsByUserId(long userId);

}
