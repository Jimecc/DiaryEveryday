package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月22日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {

    private int id;
    private int user_id;
    private int first_login;
    private int default_markdown;
    private int allow_register;

}
