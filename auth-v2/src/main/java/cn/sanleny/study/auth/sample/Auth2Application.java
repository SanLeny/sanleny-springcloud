package cn.sanleny.study.auth.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Auth2Application {

    public static void main(String args[]){
        SpringApplication.run(Auth2Application.class,args);
    }
}
