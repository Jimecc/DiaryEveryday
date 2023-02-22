package com.jimecc.diaryserver.service;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.User;
import com.jimecc.diaryserver.entity.UserRegisterParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Jim
 * @Description 用户
 * @createTime 2023年02月22日
 */

public interface UserService {

    User getUserInfo(Principal principal);

    User getUserById(int id);

    User getUserByUsername(String username);

    CommonResponse login(String username, String password, String code, HttpServletRequest request);

    CommonResponse register(UserRegisterParam registerParam);
}
