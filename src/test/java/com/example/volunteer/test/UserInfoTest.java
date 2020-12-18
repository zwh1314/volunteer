package com.example.volunteer.test;

import com.example.volunteer.Entity.UserInfo;
import com.example.volunteer.Request.UserInfoRequest;
import com.example.volunteer.Service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void userInfoTest(){
        UserInfoRequest  userInfoRequest=new UserInfoRequest();
        List<UserInfo> list=new ArrayList<UserInfo>();
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("张文瀚");
        userInfo.setAddress("攀枝花");
        userInfo.setFax("123");
        userInfo.setPriority("管理员");
        userInfo.setTel("15082361803");
        userInfo.setIntroduction("拨开云雾见太阳");
        userInfo.setHeadPicture("1314");
        userInfo.setMailAddress("2439499577@qq.com");
        userInfo.setQq("2439499577");
        list.add(userInfo);
        userInfoRequest.setUserInfoList(list);
//        userInfoService.addUserInfo(userInfoRequest);
//        System.out.println(userInfoService.getUserInfoByUserId(3L));
        userInfo.setUserId(4L);
        userInfo.setUserName("猪猪侠");
        userInfoService.updateUserInfo(userInfoRequest);
        System.out.println(userInfoService.getUserInfoByUserId(4L));
//        userInfoService.deleteUserInfoByUserId(3L);
    }
}
