package cn.sanleny.study.springcloud.ribbon.sample.controller;

import cn.sanleny.study.springcloud.ribbon.sample.command.CommandForIndex;
import cn.sanleny.study.springcloud.ribbon.sample.entity.Teacher;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: sanleny
 * @Date: 2019-04-01
 * @Description: cn.sanleny.study.springcloud.ribbon.sample.controller
 * @Version: 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user")
    public String index(@RequestParam("id")String id){
//        return restTemplate.getForObject("http://localhost:8002/user?id="+id+"",String.class);
//        return restTemplate.getForObject("http://client/user?id="+id+"",String.class);
        return new CommandForIndex(id,restTemplate).execute().toString();
    }

    //此处配置也可以在客户端配置
    @HystrixCommand(fallbackMethod = "callTimeoutFallback",
            // 配置线程池
            threadPoolProperties = { @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "1") },
            // 配置命令执行相关的，示例：超时时间
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
    @GetMapping("/teacher")
    public Object getTeacher(){
//        return restTemplate.getForEntity("http://localhost:8002/teacher",Teacher.class);
        return restTemplate.getForEntity("http://client/teacher",Teacher.class);
    }

    public Object callTimeoutFallback(){
        System.out.println("查询超时啦，我降级了>>>");
        return "查询超时啦，我降级了。";
    }

    @GetMapping("/get-for-teacher")
    public String getStringForTeacher(){
        Teacher teacher = new Teacher();
        teacher.setAge(17);
        teacher.setName("sanleny");
        teacher.setPassWord("1234");
        teacher.setHeader("good");
//        return restTemplate.postForObject("http://localhost:8002/get-teather",teacher,String.class);
        return restTemplate.postForObject("http://client/get-teather",teacher,String.class);
    }
}
