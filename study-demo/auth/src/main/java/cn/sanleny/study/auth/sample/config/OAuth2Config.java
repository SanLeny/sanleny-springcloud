//package cn.sanleny.study.auth.sample.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
///**
// * OAuth 授权服务器配置
// * @Author: sanleny
// * @Date: 2019-04-19
// * @Description: cn.sanleny.study.auth.sample.config
// * @Version: 1.0
// */
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//    //对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("").checkTokenAccess("");
//    }
//
//    //配置OAuth2的客户端相关信息
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    }
//
//    //配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    }
//
//}
