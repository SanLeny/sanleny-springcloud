package cn.sanleny.study.springcloud.client.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 *  全局异常处理
 * @Author: sanleny
 * @Date: 2019-06-20
 * @Description: cn.sanleny.study.springcloud.client
 * @Version: 1.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String,Object> exceptionHandler(Exception e){
        Map<String,Object> map =new HashMap<>();
        map.put("code",500);
        map.put("msg",e.getMessage());
        return map;
    }
 }
