package cn.sanleny.auth.config;

import cn.sanleny.auth.security.CustomClientDetailsService;
import cn.sanleny.framework.auth.common.constant.SecurityConstants;
import cn.sanleny.framework.auth.common.util.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 授权服务器配置
 * https://www.oschina.net/translate/spring-security-oauth-docs-oauth2
 * @Author: LG
 * @Date: 2020-09-19
 * @Version: 1.0
 **/
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;

    /**
     * 通过注入 AuthenticationManager 来开启密码授权
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 如果你注入一个 UserDetailsService，或者全局地配置了一个UserDetailsService（
     * 例如在 GlobalAuthenticationManagerConfigurer中），
     * 那么刷新令牌授权将包含对用户详细信息的检查，以确保该帐户仍然是活动的
     */
    private final UserDetailsService userDetailsService;

    /**
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     * @param security 定义令牌端点上的安全约束
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许表单认证
                .allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 来自 AuthorizationServerConfigurer 的一个回调,可用于定义客户端细节服务中的内存或 JDBC 实现
     * @param clients 定义客户端详细信息服务的配置器。客户详细信息可以初始化，或者可以引用现有的 store
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //自定义客户端认证
        clients.withClientDetails(customClientDetailsService());
    }

    @Bean
    public ClientDetailsService customClientDetailsService(){
        return new CustomClientDetailsService();

    }

    /**
     * pathMapping() 框架提供的 URL 路径是/oauth/authorize（授权端点），/oauth/token（令牌端点），/oauth/confirm_access（用户在这里发布授权批准），/oauth/error（用于在授权服务器上渲染错误），/oauth/check_token（由资源服务器用来解码访问令牌）和/oauth/token_key（如果使用JWT令牌，公开密钥用于令牌验证）
     *
     * 配置身份认证器，配置认证方式
     * @param endpoints 定义授权和令牌端点以及令牌服务
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        endpoints.tokenStore(tokenStore).tokenEnhancer(tokenEnhancerChain)
//        .tokenEnhancer(jwtAccessTokenConverter()) //等价于accessTokenConverter(jwtTokenEnhancer())
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
    }

    /**
     * 增强 token
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(4);
            CustomUser customUser = (CustomUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put(SecurityConstants.TOKEN_USER_ID, customUser.getId());
            additionalInfo.put(SecurityConstants.TOKEN_USERNAME, customUser.getUsername());
            additionalInfo.put(SecurityConstants.TOKEN_TENANT_ID, customUser.getTenantId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        //注意此处需要相应的jks文件
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
        return converter;
    }

}
