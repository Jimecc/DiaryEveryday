package com.jimecc.diaryserver.controller;

import com.jimecc.diaryserver.entity.User;
import com.jimecc.diaryserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Jim
 * @Description 用户接口
 * @createTime 2023年02月23日
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/info")
    public User getAdminInfo(Principal principal){
        return userService.getUserInfo(principal);
    }
}
