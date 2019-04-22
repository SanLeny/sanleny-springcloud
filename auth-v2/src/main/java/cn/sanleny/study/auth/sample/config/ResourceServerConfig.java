//package cn.sanleny.study.auth.sample.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * 自定义资源服务器
// * @Author: sanleny
// * @Date: 2019-04-20
// * @Description: cn.sanleny.study.auth.sample.config
// * @Version: 1.0
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    /**
//     * http 安全配置，对外暴露
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //使用的是JWT，禁用 csrf
//        http.csrf().disable()
//        //Spring Security不会创建HttpSession，不使用 SecurityContext
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        ///配置访问控制
//        .requestMatchers().antMatchers("/**")
//        .and()
//        //不需要认证的路径
//        .authorizeRequests().antMatchers("/login","/index","/oauth/**").permitAll()
//        .and()
//        .authorizeRequests().anyRequest().authenticated();
//    }
//
//    /**
//     * 资源安全配置相关
//     * @param resources
//     * @throws Exception
//     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        super.configure(resources);
//    }
//}
