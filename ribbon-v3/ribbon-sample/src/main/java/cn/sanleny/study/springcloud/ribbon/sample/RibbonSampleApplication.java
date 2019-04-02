package cn.sanleny.study.springcloud.ribbon.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker//客户端的服务断路器
//@EnableEurekaClient
//@RibbonClient(name = "client", configuration = CustomConfiguration.class) //自定义 RibbonClient configuration
public class RibbonSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonSampleApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate template(){
        return new RestTemplate();
    }

}
