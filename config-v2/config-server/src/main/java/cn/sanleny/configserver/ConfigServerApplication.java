package cn.sanleny.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * http://localhost:5000/configClient/dev  查看配置
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient//配置中心和注册中心结合使用  引入jar,将自己定义为一个客户端
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
