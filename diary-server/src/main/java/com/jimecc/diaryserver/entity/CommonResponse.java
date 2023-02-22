package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jim
 * @Description Common Response / 通用回复信息
 * @createTime 2023年02月23日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {

    private int status;
    private String message;
    private Object obj;

    public static CommonResponse success(String message){
        return new CommonResponse(200,message,null);
    }

    public static CommonResponse success(String message,Object obj){
        return new CommonResponse(200,message,obj);
    }

    public static CommonResponse error(String message){
        return new CommonResponse(400,message,null);
    }

    public static CommonResponse error(String message,Object obj){
        return new CommonResponse(400,message,obj);
    }

}
