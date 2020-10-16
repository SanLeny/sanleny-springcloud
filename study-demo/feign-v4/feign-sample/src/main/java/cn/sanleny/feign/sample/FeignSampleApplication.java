package cn.sanleny.feign.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@SpringBootApplication
@EnableHystrix
@EnableFeignClients //启用feign，如果说需要启动默认配置，就这么写，如果需要覆盖就写成(@EnableFeignClients(defaultConfiguration = "FooConfiguartion.class"))
public class FeignSampleApplication {

    public static void main(String args[]){
        SpringApplication.run(FeignSampleApplication.class,args);
    }
}
