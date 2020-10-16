package cn.sanleny.study.auth.sample.config;

import cn.sanleny.study.auth.sample.security.MyAuthenticationProvider;
import cn.sanleny.study.auth.sample.security.filter.CustomAuthenticationFilter;
import cn.sanleny.study.auth.sample.security.filter.CustomLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * @Author: sanleny
 * @Date: 2019-04-19
 * @Description: cn.sanleny.study.auth.sample.config
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法级的权限认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private CustomAuthenticationProvider customAuthenticationProvider;

    /**
     * 使用自定义身份验证组件
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new MyAuthenticationProvider(userDetailsService));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean() ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用的是JWT，禁用 csrf
        http.csrf().disable()
        //Spring Security不会创建HttpSession，不使用 SecurityContext
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests().antMatchers("/login","/index","/oauth/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .logout().logoutUrl("/logout")
        .clearAuthentication(true)
        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
        //add自定义 LogoutHandler
        .addLogoutHandler(new CustomLogoutHandler())
        // 加入自定义 UsernamePasswordAuthenticationFilter 替代原有Filter
        .and().addFilterBefore(new CustomAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Override this method to configure {@link WebSecurity}. For example, if you wish to
     * ignore certain requests.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ioc");
    }
}
