package com.jimecc.diaryserver.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jim
 * @Description 设置
 * @createTime 2023年02月22日
 */
@Mapper
public interface SettingMapper {

    boolean DefaultMarkdown(int user_id);

    boolean FirstLogin(int user_id);

    boolean UpdateDefaultMarkdown(int user_id,int value);

}
