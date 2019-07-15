package cn.sanleny.study.springcloud.adminServer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: sanleny
 * @Date: 2019-07-15
 * @Description: cn.sanleny.study.springcloud.adminServer
 * @Version: 1.0
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class,args);
    }
}
