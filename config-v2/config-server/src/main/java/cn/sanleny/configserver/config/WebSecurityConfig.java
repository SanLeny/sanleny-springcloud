package cn.sanleny.configserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 自定义 WebSecurityConfig
 * 配置文件application.yml 加入配置--启用加密算法
 * 添加bootstrap.yml添加秘钥
 * @Author: sanleny
 * @Date: 2019-03-28
 * @Description: cn.sanleny.configserver.config
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        // 在 @EnableWebSecurity配置中，禁用CSRF。
        http.csrf().disable();
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,
        //http://sanleny:123456@localhost:5000/encrypt/
        // 如果是form方式,不能使用url格式登录
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
