package com.jimecc.diaryserver.utils;

import com.jimecc.diaryserver.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    
    /**
     * @Author: Jim
     * @Description: 获取当前登录操作员
     * @Params: 
     */
    public static User getCurrentAdmin(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static int getId(){
        return getCurrentAdmin().getId();
    }
}
