package cn.sanleny.feign.sample.controller;

import cn.sanleny.feign.sample.service.ClientSmapleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sanleny
 * @Date: 2019-04-03
 * @Description: cn.sanleny.feign.sample.controller
 * @Version: 1.0
 */
@RestController
public class FeignConsumerController {

    @Autowired
    private ClientSmapleService clientSmapleService;

    @GetMapping("/teacher")
    public Object getTeacher(){
        return  clientSmapleService.getTeacher();
    }

    @GetMapping("/user")
    public String findTeacher(String id){
        return  clientSmapleService.getByTeacher(id);
    }

    @PostMapping("/order")
    public String findTeacher1(String orderNo){
        System.out.println(orderNo);
        return  clientSmapleService.getByTeacher1(orderNo);
    }

    @GetMapping("/testuser/getUser")
    public String getUser(){
        return clientSmapleService.getUser();
    }

}
