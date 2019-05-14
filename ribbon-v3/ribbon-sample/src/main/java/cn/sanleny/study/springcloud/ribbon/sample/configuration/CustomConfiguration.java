package cn.sanleny.study.springcloud.ribbon.sample.configuration;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: sanleny
 * @Date: 2019-04-02
 * @Description: cn.sanleny.study.springcloud.ribbon.sample.configuration
 * @Version: 1.0
 */
@Configuration
public class CustomConfiguration {

//    @Bean
//    public IPing ribbonPing() {
//        // 负载均衡规则，改为随机
//        return new PingUrl();
//    }

//    @Bean
//    public IRule ribbonRule() { // 其中IRule就是所有规则的标准
//        return new com.netflix.loadbalancer.WeightedResponseTimeRule(); // 随机的访问策略
//    }

}
