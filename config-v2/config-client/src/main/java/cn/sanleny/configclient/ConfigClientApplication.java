package cn.sanleny.configclient;

import cn.sanleny.configclient.sample.EventConfiguration;
import cn.sanleny.configclient.sample.MyApplicationEvent;
import cn.sanleny.configclient.sample.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8002/actuator/env  查看当前配置中心内部的配置信息
 * post http://localhost:8002/actuator/refresh  刷新获取到修改后最新的配置
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@RefreshScope  //定义作用域，表示在这个注解下的bean会被刷新（只有加了这个，发刷新请求时，数据才会变更）
public class ConfigClientApplication {

    public static void main(String[] args) {
//        publishEvent();
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    /**
     * spring 内部事件
     */
    private static void publishEvent() {
        //创建spring 基于注解驱动的应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册到sping上下文中
        context.register(EventConfiguration.class);
        //启动spring上下文
        context.refresh();
        ApplicationEventPublisher publisher = context;
        //发布一个事件
        publisher.publishEvent(new MyApplicationEvent("》》》》发布一个事件"));
    }

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @GetMapping("/index")
    public Object index(){
        return new User(name,age);
    }

}
