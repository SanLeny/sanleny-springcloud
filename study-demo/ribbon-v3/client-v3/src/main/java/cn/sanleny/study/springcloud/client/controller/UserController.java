package cn.sanleny.study.springcloud.client.controller;

import cn.sanleny.study.springcloud.client.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: sanleny
 * @Date: 2019-04-01
 * @Description: cn.sanleny.study.springcloud.server.controller
 * @Version: 1.0
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping("user")
    public String user(String id){
        log.info("user:这是客户端v3:已经接到了客户端发来的请求:");
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("user:这是客户端v3返回的请求_"+id);
        return "这是客户端v3返回的请求_"+id;
    }

    @GetMapping("teacher")
    public Teacher teacher(){
        log.info("teacher:这是客户端v3返回的请求_");
        return new Teacher("teacher",20,"v3","header");
    }

    @PostMapping("get-teather")
    public String postForTeacher(@RequestBody Object object){
        log.info("get-teather:这是客户端v3返回的请求_");
        return  object.toString();
    }

    @RequestMapping("/order")
    public String order(@RequestBody String orderNo){
        log.info("这是客户端v3返回的请求_"+orderNo);
        return "这是客户端v3返回的请求_"+orderNo;
    }
}
