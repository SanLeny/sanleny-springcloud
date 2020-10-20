package cn.sanleny.framework.auth.boot.autoconfigure;

import cn.sanleny.frameword.core.boot.autoconfigure.PermitAllUrlProperties;
import cn.sanleny.framework.auth.exception.CustomAccessDeniedHandler;
import cn.sanleny.framework.auth.exception.AuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author: LG
 * @Date: 2020-09-21
 * @Version: 1.0
 **/
@Configuration
@EnableResourceServer
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法级的权限认证
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final RedisConnectionFactory redisConnectionFactory;

    private final PermitAllUrlProperties permitAllUrlProperties;

    private final AuthProperties authProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(permitAllUrlProperties.getUrls().stream().toArray(String[]::new)).permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore())
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    /**
     * token 存储仓库
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(authProperties.getTokenPrefix());
        return tokenStore;
    }
}
