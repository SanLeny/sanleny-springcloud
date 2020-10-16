package cn.sanleny.study.springcloud.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String args[]){
        SpringApplication.run(GatewayApplication.class,args);
    }
}
