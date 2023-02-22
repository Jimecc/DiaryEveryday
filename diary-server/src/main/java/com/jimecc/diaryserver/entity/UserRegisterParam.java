package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jim
 * @Description 用户注册参数
 * @createTime 2023年02月23日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterParam {

    private String name;
    private String username;
    private String password1;
    private String password2;

}
