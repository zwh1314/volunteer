package com.example.volunteer.Service;

import com.example.volunteer.DTO.UserInfoDTO;
import com.example.volunteer.Request.UserInfoRequest;

public interface UserInfoService {
    UserInfoDTO getUserInfoByUserId(long userId);

    boolean addUserInfo(UserInfoRequest userInfoRequest);

    boolean updateUserInfo(UserInfoRequest userInfoRequest);

    boolean deleteUserInfoByUserId(long userId);
}
