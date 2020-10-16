package cn.sanleny.study.springcloud.ribbon.sample.configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
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

    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则，改为随机
        return new RandomRule();
//        return new WeightedResponseTimeRule();
    }

}
