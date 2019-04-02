package cn.sanleny.study.springcloud.client.controller;

import cn.sanleny.study.springcloud.client.entity.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sanleny
 * @Date: 2019-04-01
 * @Description: cn.sanleny.study.springcloud.server.controller
 * @Version: 1.0
 */
@RestController
public class UserController {

    @GetMapping("user")
    public String user(String id){
        System.out.println("user:这是客户端v3:已经接到了客户端发来的请求:");
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("user:这是客户端v3返回的请求_"+id);
        return "这是客户端v3返回的请求_"+id;
    }

    @GetMapping("teacher")
    public Teacher teacher(){
        System.out.println("teacher:这是客户端v3返回的请求_");
        return new Teacher("teacher",20,"v3","header");
    }

    @PostMapping("get-teather")
    public String postForTeacher(@RequestBody Object object){
        System.out.println("get-teather:这是客户端v3返回的请求_");
        return  object.toString();
    }
}
