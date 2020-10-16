package cn.sanleny.study.springcloud.clien.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sanleny
 * @Date: 2019-04-01
 * @Description: cn.sanleny.study.springcloud.server.controller
 * @Version: 1.0
 */
@RestController
@Slf4j
public class UserController {

    @Value("${server.port}")
    private int port;

    @GetMapping("user")
    public String user(String id){
        log.info("user:这是客户端v3:已经接到了客户端发来的请求:");
        log.info("user:这是客户端v3返回的请求_"+id);
        return "这是客户端"+ port +"返回的请求_"+id;
    }

}
