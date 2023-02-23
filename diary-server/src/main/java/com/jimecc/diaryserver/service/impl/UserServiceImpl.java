package com.jimecc.diaryserver.service.impl;

import com.jimecc.diaryserver.config.JwtTokenUtil;
import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.User;
import com.jimecc.diaryserver.entity.UserRegisterParam;
import com.jimecc.diaryserver.mapper.UserMapper;
import com.jimecc.diaryserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jim
 * @Description 用户
 * @createTime 2023年02月22日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserInfo(Principal principal) {
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        User user = userMapper.GetUserByUsername(username);
        user.setPassword(null);
        return user;

    }

    public User getUserById(int id){
        return userMapper.GetUserById(id);
    }

    public User getUserByUsername(String username) {
        return userMapper.GetUserByUsername(username);
    }

    public CommonResponse login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String)request.getSession().getAttribute("captcha");
        if(null==code||!captcha.equalsIgnoreCase(code)){
            return CommonResponse.selErr("验证码输入错误，请重新输入！");
        }

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if(null == user || ! passwordEncoder.matches(password,user.getPassword())){
            return CommonResponse.selErr("用户名或密码不正确");
        }

        // 更新 security 登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成 token
        String token = jwtTokenUtil.generateToken(user);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResponse.selSuc("登陆成功",tokenMap);
    }

    public CommonResponse register(UserRegisterParam registerParam){
        if(registerParam.getName()==null || registerParam.getUsername()==null || registerParam.getPassword1()==null || registerParam.getPassword2()==null){
            return CommonResponse.selErr("字段不完整");
        }
        if(!registerParam.getPassword1().equals(registerParam.getPassword2())){
            return CommonResponse.selErr("两次密码不一致");
        }

        User user = userMapper.GetUserByUsername(registerParam.getUsername());
        if(user != null){
            return CommonResponse.selErr("用户已存在");
        }

        User newuser = new User();
        newuser.setName(registerParam.getName());
        newuser.setUsername(registerParam.getUsername());
        newuser.setPassword(passwordEncoder.encode(registerParam.getPassword1()));
        newuser.setCreate_date(new Date());
        int result = userMapper.CreateUser(newuser);
        if(result == 1){
            return CommonResponse.success("注册成功！");
        }
        return CommonResponse.error("注册失败");
    }
    
}
