package cn.sanleny.frameword.core.common.util;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * REST API 返回结果
 * @Author: LG
 * @Date: 2019-05-06
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int code = HttpStatus.HTTP_OK;  //状态码

    @Getter
    @Setter
    private String msg = "success";     //状态描述

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private T data;         //结果集

    public ResultData(Throwable e) {
        this.msg = e.getMessage();
        this.code = HttpStatus.HTTP_INTERNAL_ERROR;
    }


    public static <T> ResultData<T> succeed() {
        return resultData(null,null,null);
    }


    public static <T> ResultData<T> succeed(T data) {
        return resultData(data,null,null);
    }

    public static <T> ResultData<T> succeed(T data, String msg) {
        return resultData(data,null,msg);
    }


    public static <T> ResultData<T> failed(String msg) {
        return resultData(null,HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public static <T> ResultData<T> failed(int code, String msg) {
        return resultData(null,code,msg);
    }

    public static <T> ResultData<T> failed(T data) {
        return resultData(data, HttpStatus.HTTP_INTERNAL_ERROR, null);
    }

    private static <T> ResultData<T> resultData(T data, Integer code, String msg) {
        ResultData<T> resultData = new ResultData<>();
        if(null != code) resultData.setCode(code);
        if(null != msg) resultData.setMsg(msg);
        if(null != data) resultData.setData(data);
        return resultData;
    }

}
