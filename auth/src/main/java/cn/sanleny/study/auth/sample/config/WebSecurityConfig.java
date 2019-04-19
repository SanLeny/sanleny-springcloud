package cn.sanleny.study.auth.sample.config;

import cn.sanleny.study.auth.sample.security.JwtAuthenticationFilter;
import cn.sanleny.study.auth.sample.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * 自定义 WebSecurityConfig
 * @Author: sanleny
 * @Date: 2019-04-18
 * @Description: cn.sanleny.study.auth.sample.config
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用方法级的权限认证
//Spring Security默认是禁用注解的，开启方法：需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，并在该类中将AuthenticationManager定义为Bean
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @see cn.sanleny.study.auth.sample.security.UserDetailsServiceImpl
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * By default, Spring Security’s OAuth2LoginAuthenticationFilter only processes URLs matching /login/oauth2/code/*.
     * If you want to customize the redirect-uri to use a different pattern, you need to provide configuration to process
     * that custom pattern.
     * For example, for servlet applications, you can add your own WebSecurityConfigurerAdapter that resembles the following:
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf,
        http.csrf().disable()
                .authorizeRequests()
                //允许访问
                // 首页和登录页面
                .antMatchers("/","/login").permitAll()
                // 服务监控
                .antMatchers("/actuator/**").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout")
                .clearAuthentication(true)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                // 加入自定义UsernamePasswordAuthenticationFilter替代原有Filter
                .and().addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 使用自定义身份验证组件
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
