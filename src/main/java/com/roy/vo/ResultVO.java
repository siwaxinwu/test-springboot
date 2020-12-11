package com.roy.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.roy.enums.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description：前端交互对象
 * author：dingyawu
 * date：created in 23:09 2020/8/20
 * history:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    /** 后端处理成功是否标志*/
    private boolean success;
    /** 错误码*/
    private String code;
    /** 错误消息*/
    private String msg;
    /** 返回值*/
    private T data;

    public static <T> ResultVO success(String msg, T data){
        return new ResultVO(true, "200", msg, data);
    }

    public static <T> ResultVO success(){
        return new ResultVO(){{
            setSuccess(true);
        }};
    }

    public static <T> ResultVO fail(Error error, T data){
        return new ResultVO(false, error.getCode(), error.getMsg(), data);
    }
}
