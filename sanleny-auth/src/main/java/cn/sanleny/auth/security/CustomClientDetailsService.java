package cn.sanleny.auth.security;

import cn.hutool.core.util.StrUtil;
import cn.sanleny.framework.auth.boot.autoconfigure.AuthProperties;
import cn.sanleny.framework.auth.common.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;

/**
 * 自定义客户端认证
 * @Author: LG
 * @Date: 2020-09-19
 * @Version: 1.0
 **/
@Slf4j
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private AuthProperties authProperties;

    /**
     * clientId: （必须的）客户端 id
     * secret: （要求用于受信任的客户端）客户端的机密，如果有的话
     * scope: 客户范围限制。如果范围未定义或为空（默认），客户端将不受范围限制
     * authorizedGrantTypes: 授权客户端使用的授予类型。默认值为空
     * authorities: 授权给客户的认证（常规 Spring Security 认证）
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        //TODO 这里可以做客户端验证
        if(!StrUtil.equals(clientId,authProperties.getClientId())){
            log.warn(">>> No client width requested id：{}",clientId);
            throw new NoSuchClientException("No client width requested id: " + clientId);
        }
        BaseClientDetails client = new BaseClientDetails();
        client.setClientId(clientId);
        client.setClientSecret(SecurityConstants.BCRYPT + new BCryptPasswordEncoder().encode(authProperties.getClientSecret()));
        //不同的client可以通过 一个scope 对应 权限集
        client.setScope(authProperties.getScope());
        // 授权码（authorization_code）、隐藏式（implicit）、密码式（password）、客户端凭证（client_credentials）
        client.setAuthorizedGrantTypes(Arrays.asList("authorization_code","client_credentials", "refresh_token", "password", "implicit"));
        //client.setAuthorities(AuthorityUtils.createAuthorityList("list:list:list","add:add:add","edit:edit:edit","ROLE_USER"));
        client.setAccessTokenValiditySeconds(authProperties.getTokenValidityTime());
        client.setRefreshTokenValiditySeconds(authProperties.getTokenValidityTime());
        if(client == null) {
            log.warn(">>>>> No client width requested id: {}", clientId );
            throw new NoSuchClientException("No client width requested id: " + clientId);
        }
        return client;
    }
}
