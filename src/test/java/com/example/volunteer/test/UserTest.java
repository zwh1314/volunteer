package com.example.volunteer.test;

import com.example.volunteer.Entity.User;
import com.example.volunteer.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void firstTest() {
//        User user1=new User();
//        //user1.setId(1);
//        user1.setUsername("张文瀚");
//        user1.setPassword("991219");
//        user1.setHeadPictureStr("123456");
//        user1.setEmail("2439499577@qq.com");
//        user1.setUserSentence("拨开云雾见太阳");
//        userService.addUser(user1);
          System.out.println(userService.findUserById(0));
    }
}
