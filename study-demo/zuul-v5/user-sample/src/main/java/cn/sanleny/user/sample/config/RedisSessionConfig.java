package cn.sanleny.user.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: sanleny
 * @Date: 2019-04-17
 * @Description: cn.sanleny.user.sample.config
 * @Version: 1.0
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
