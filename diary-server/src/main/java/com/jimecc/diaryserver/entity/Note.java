package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月22日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private int id;
    private int user_id;
    private String info;
    private String title;
    private String article;
    private Date create_date;
    private Date update_date;
    private String color;
    private String weather;
    private int score;
    private int collection;
    private int shit;
    private int delete_or_not;
    private int hidden;

    public Note(int user_id){
        this.user_id = user_id;
    }
}
