package cn.sanleny.study.springcloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class,args);
    }
}
