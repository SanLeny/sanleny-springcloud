package cn.sanleny.study.springcloud.client.controller;

import cn.sanleny.study.springcloud.client.entity.Teacher;
import cn.sanleny.study.springcloud.client.service.MovieService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

/**
 * @Author: sanleny
 * @Date: 2019-04-01
 * @Description: cn.sanleny.study.springcloud.server.controller
 * @Version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private MovieService batchService;

    @RequestMapping("user")
    public String user(String id){
        System.out.println("user:这是客户端v2:已经接到了客户端发来的请求:");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("user:这是客户端v2返回的请求_"+id);
        return "这是客户端v2返回的请求_"+id;
    }

    @HystrixCommand(fallbackMethod = "callTimeoutFallback",
            // 配置线程池
            threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "1") },
            // 配置命令执行相关的，示例：超时时间
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") })
    @GetMapping("teacher")
    public Teacher teacher(){
        int sleepTime = new Random().nextInt(3000);
        System.out.println("teacher:这是客户端v2返回的请求_"+",开始休眠:"+sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Teacher("teacher",20,"12312","header");
    }

    public Teacher callTimeoutFallback(){
        System.out.println("teacher:这是客户端v2:查询超时啦，我降级了>>>");
        return new Teacher("teacher",20,"12312","teacher:这是客户端v2:查询超时啦，我降级了>>>");
    }

    @PostMapping("get-teather")
    public String postForTeacher(@RequestBody Object object){
        System.out.println("get-teather:这是客户端v2返回的请求_");
        return  object.toString();
    }

    @RequestMapping("/movie/query")
    public Map<String, Object> queryMovie(String movieCode) throws Exception {
        return batchService.queryMovie(movieCode);
    }

    @RequestMapping("/order")
    public String order(@RequestBody String orderNo){
        System.out.println("这是客户端v2返回的请求_"+orderNo);
        return "这是客户端v2返回的请求_"+orderNo;
    }

}
