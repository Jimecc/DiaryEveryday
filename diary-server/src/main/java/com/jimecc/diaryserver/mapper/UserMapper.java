package com.jimecc.diaryserver.mapper;

import com.jimecc.diaryserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jim
 * @Description 用户
 * @createTime 2023年02月22日
 */
@Mapper
public interface UserMapper {

    User GetUserById(int id);

    User GetUserByUsername(String username);

    int CreateUser(User user);

}
