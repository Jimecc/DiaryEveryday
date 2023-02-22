package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jim
 * @Description 用户登录参数
 * @createTime 2023年02月23日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginParam {

    private String username;
    private String password;
    private String code;

}
