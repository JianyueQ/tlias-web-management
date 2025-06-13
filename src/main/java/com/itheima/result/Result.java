package com.itheima.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result{
    private Integer code;
    private String message;
    private Object data;

    /**
     * 成功 返回结果
     */
    public static Result success(Object data){
        return new Result(1,"操作成功",data);
    }

    /**
     * 成功
     */
    public static Result success(){
        return new Result(1,"操作成功",null);
    }

    /**
     * 失败
     */
    public static Result error(String message){
        return new Result(0,message,null);
    }
}
