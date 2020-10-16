package cn.sanleny.study.springcloud.clien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: sanleny
 * @Date: 2019-03-05
 * @Description: cn.sanleny.study.springcloud.v1
 * @Version: 1.0
 */
@SpringBootApplication
public class RibbonClientApplication {

    public static void main(String args[]){
        SpringApplication.run(RibbonClientApplication.class,args);
    }
}
