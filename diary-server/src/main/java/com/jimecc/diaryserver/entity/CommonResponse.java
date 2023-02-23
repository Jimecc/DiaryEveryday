package com.jimecc.diaryserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jim
 * @Description Common Response / 通用回复信息
 * @createTime 2023年02月23日
 * 状态码描述：
 *  查询/通用：200成功 400失败
 *  添加：201成功 451失败
 *  删除：202成功 452失败
 *  更新：203成功 453失败
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

    public static CommonResponse selSuc(String message){
        return new CommonResponse(200,message,null);
    }

    public static CommonResponse selSuc(String message,Object obj){
        return new CommonResponse(200,message,obj);
    }

    public static CommonResponse selErr(String message){
        return new CommonResponse(400,message,null);
    }

    public static CommonResponse selErr(String message,Object obj){
        return new CommonResponse(400,message,obj);
    }

    public static CommonResponse delSuc(String message){return new CommonResponse(202,message,null);}

    public static CommonResponse delSuc(String message,Object obj){return new CommonResponse(202,message,obj);}

    public static CommonResponse delErr(String message){return new CommonResponse(452,message,null);}

    public static CommonResponse delErr(String message,Object obj){return new CommonResponse(452,message,obj);}

    public static CommonResponse putSuc(String message){return new CommonResponse(201,message,null);}

    public static CommonResponse putSuc(String message,Object obj){return new CommonResponse(201,message,obj);}

    public static CommonResponse putErr(String message){return new CommonResponse(451,message,null);}

    public static CommonResponse putErr(String message,Object obj){return new CommonResponse(451,message,obj);}

    public static CommonResponse postSuc(String message){return new CommonResponse(203,message,null);}

    public static CommonResponse postSuc(String message,Object obj){return new CommonResponse(203,message,obj);}

    public static CommonResponse postErr(String message){return new CommonResponse(453,message,null);}

    public static CommonResponse postErr(String message,Object obj){return new CommonResponse(453,message,obj);}


}
