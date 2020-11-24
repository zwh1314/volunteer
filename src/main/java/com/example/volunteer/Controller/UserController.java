package com.example.volunteer.Controller;

import com.example.volunteer.Response.Response;
import com.example.volunteer.Service.UserService;
import com.example.volunteer.ServiceImpl.UserServicepImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userervice;

    @GetMapping("/signIn")
    @ApiOperation("登录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "tel", value = "手机号", paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "password", value = "账户密码", paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "verifyCode", value = "短信验证码", paramType = "query", dataType = "String"),
//    })
    public Response signIn(//@RequestParam("tel") String tel,
                           //@RequestParam("password") String password,
                           //@RequestParam("verifyCode") String verifyCode
                           ) {
        System.out.println("进入");
        Response response = new Response();
        System.out.println(userervice.findUserById(0));
//        try {
//            validateUserInfoAndVerifyCode(tel, password, verifyCode);
//
//            response.setSuc(userService.signIn(tel, password, verifyCode));
//        } catch (IllegalArgumentException e) {
//            logger.warn("[signIn Illegal Argument], tel: {}, password: {}", tel, password, e);
//            response.setFail(com.example.volunteer.ResponseEnum.ILLEGAL_PARAM);
//            return response;
//        } catch (SteelRuntimeException e) {
//            logger.error("[signIn Runtime Exception], tel: {}, password: {}", tel, password, e);
//            response.setFail(e.getExceptionCode(), e.getMessage());
//            return response;
//        }  catch (Exception e) {
//            logger.error("[signIn Exception], tel: {}, password: {}", tel, password, e);
//            response.setFail(responseenum.SERVER_ERROR);
//            return response;
//        }

        return response;
    }
}
