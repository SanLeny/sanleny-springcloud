package cn.sanleny.zuul.sample.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: sanleny
 * @Date: 2019-04-04
 * @Description: cn.sanleny.zuul.sample.configuration
 * @Version: 1.0
 */
@Component
@ConfigurationProperties("my.zuul.token-filter")
public class MyConfiguration {

    private List<String> noAuthenticationRoutes;

    public List<String> getNoAuthenticationRoutes() {
        return noAuthenticationRoutes;
    }

    public void setNoAuthenticationRoutes(List<String> noAuthenticationRoutes) {
        this.noAuthenticationRoutes = noAuthenticationRoutes;
    }
}
