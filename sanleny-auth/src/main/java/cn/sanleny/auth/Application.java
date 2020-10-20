package cn.sanleny.auth;

import cn.sanleny.framework.service.boot.autoconfigure.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(defaultConfiguration = FeignConfiguration.class, basePackages = {"cn.sanleny"})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.sanleny"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
