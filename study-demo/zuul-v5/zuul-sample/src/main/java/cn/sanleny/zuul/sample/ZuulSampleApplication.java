package cn.sanleny.zuul.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulSampleApplication {

    public static void main(String args[]){
        SpringApplication.run(ZuulSampleApplication.class,args);
    }
}
