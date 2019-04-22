package cn.sanleny.study.auth.sample.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 自定义客户端认证
 * @Author: sanleny
 * @Date: 2019-04-19
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
@Slf4j
public class CustomClientDetailsService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println(">>>>>>>>loadClientByClientId:"+clientId);
        BaseClientDetails client = null;
        //这里可以改为查询数据库
        if("1".equals(clientId)) {
            client = new BaseClientDetails();
            client.setClientId(clientId);
            client.setClientSecret("{noop}123456789");
            //client.setResourceIds(Arrays.asList("order"));
            client.setAuthorizedGrantTypes(Arrays.asList("authorization_code","client_credentials", "refresh_token", "password", "implicit"));
            //不同的client可以通过 一个scope 对应 权限集
            client.setScope(Arrays.asList("all", "select"));
            client.setAuthorities(AuthorityUtils.createAuthorityList("add:add:add","edit:edit:edit","ROLE_USER"));
            client.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(5));//5分钟过期
            client.setRefreshTokenValiditySeconds((int)TimeUnit.MINUTES.toSeconds(5));//5分钟过期
//            client.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); //1天
//            client.setRefreshTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(1)); //1天

            Set<String> uris = new HashSet<>();
            uris.add("http://localhost:9080/login");
            client.setRegisteredRedirectUri(uris);
        }
        if(client == null) {
            throw new NoSuchClientException("No client width requested id: " + clientId);
        }
        return client;
    }
}
