package cn.sanleny.study.springcloud.client;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
public class Client3Application {

    public static void main(String args[]){
        SpringApplication.run(Client3Application.class,args);
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
