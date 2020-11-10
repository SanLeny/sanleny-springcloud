package cn.sanleny.framework.auth.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;

/**
 * 认证相关
 * @Author: LG
 * @Date: 2020-09-22
 * @Version: 1.0
 **/
@Data
@Configuration
@ConfigurationProperties("sanleny.auth")
public class AuthProperties {

    /**
     * 客户端ID
     */
    private String clientId = "cloud";

    /**
     * 客户端密码
     */
    private String clientSecret = "123456";

    /**
     * 授权类型: password、client_credentials
     */
    private String grantType = "password";

    /**
     * 权限范围
     */
    private Collection<String> scope = Arrays.asList("all", "select");

    /**
     * 签名key
     */
    private String signingKey = "123";
    /**
     * token key 前缀
     */
    private String tokenPrefix = "cloud:oauth:";

    /**
     * token 过期时间
     */
    private int tokenValidityTime = 3000;



}
