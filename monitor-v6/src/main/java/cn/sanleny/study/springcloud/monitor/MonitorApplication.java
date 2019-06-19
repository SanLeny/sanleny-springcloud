package cn.sanleny.study.springcloud.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * http://localhost:7003/hystrix
 * http://localhost:8002/actuator/hystrix.stream
 * http://localhost:7003/turbine.stream
 * @Author: sanleny
 * @Date: 2019-06-18
 * @Description: cn.sanleny.study.springcloud.monitor
 * @Version: 1.0
 */
@EnableHystrixDashboard
@EnableTurbine
@SpringBootApplication
@EnableAdminServer
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class,args);
    }
}
