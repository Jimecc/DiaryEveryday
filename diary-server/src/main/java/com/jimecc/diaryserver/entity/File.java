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
public class File {

    private int id;
    private String name;
    private String info;
    private String url;
    private String rname;
    private String remoteFileName;
    private String groupName;
    private int user_id;
    private int note_id;
    private Date create_date;

}
