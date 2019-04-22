package cn.sanleny.study.auth.sample.config;

import cn.sanleny.study.auth.sample.security.CustomClientDetailsService;
import cn.sanleny.study.auth.sample.security.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * OAuth 授权服务器配置
 * @Author: sanleny
 * @Date: 2019-04-19
 * @Description: cn.sanleny.study.auth.sample.config
 * @Version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    //认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//    @Autowired
//    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    //对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许表单认证
//                .allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置OAuth2的客户端相关信息
     * ClientDetailsServiceConfigurer (AuthorizationServerConfigurer 的一个回调配置项)
     * 能够使用内存或者JDBC来实现客户端详情服务（ClientDetailsService）
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //自定义客户端认证
        clients.withClientDetails(customClientDetailsService());
    }

    //配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置token的数据源、自定义的tokenServices等信息
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore())
                .tokenServices(authorizationServerTokenServices())
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtTokenConverter())//等于下面的 .tokenEnhancer(jwtTokenConverter())
//                .tokenEnhancer(jwtTokenConverter())
        // 自定义实现类Implements WebResponseExceptionTranslator 接口处理异常装换逻辑
//                .exceptionTranslator(webResponseExceptionTranslator)

        ;
    }

    @Bean
    public ClientDetailsService customClientDetailsService(){
        return new CustomClientDetailsService();
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }

//    @Bean
//    public TokenStore redisTokenStore() {
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        return tokenStore;
//    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        //自定义JwtAccessToken转换器
        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        converter.setSigningKey("secret");
        return converter;
    }

    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
//        CustomAuthorizationTokenServices customTokenServices = new CustomAuthorizationTokenServices();
//        customTokenServices.setTokenStore(redisTokenStore());
//        customTokenServices.setSupportRefreshToken(true);
//        customTokenServices.setReuseRefreshToken(true);
//        customTokenServices.setClientDetailsService(new CustomClientDetailsService());
//        customTokenServices.setTokenEnhancer(jwtTokenConverter());
//        return customTokenServices;
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(jwtTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(customClientDetailsService());
        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }


}
