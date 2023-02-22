package com.jimecc.diaryserver.controller;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.User;
import com.jimecc.diaryserver.entity.UserLoginParam;
import com.jimecc.diaryserver.entity.UserRegisterParam;
import com.jimecc.diaryserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月13日
 */

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService ;

    @PostMapping("/login")
    public CommonResponse login (@RequestBody UserLoginParam loginParam, HttpServletRequest request){
        return userService.login(loginParam.getUsername(),loginParam.getPassword(),loginParam.getCode(),request);
    }

    @GetMapping("/logout")
    public CommonResponse logout(){
        return CommonResponse.success("退出成功");
    }

    @PostMapping("/register")
    public CommonResponse register(@RequestBody UserRegisterParam registerParam){
        return userService.register(registerParam);
    }

}