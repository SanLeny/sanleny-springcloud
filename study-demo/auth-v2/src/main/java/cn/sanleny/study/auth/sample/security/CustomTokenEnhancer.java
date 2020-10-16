package cn.sanleny.study.auth.sample.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义JwtAccessToken转换器
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    private static final String TOKEN_SEG_USER_ID = "userId";
    private static final String TOKEN_SEG_CLIENT = "clientId";

    /**
     * 生成token
     * 增强token方法,用于自定义一些token总需要封装的信息
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Object principal = authentication.getUserAuthentication().getPrincipal();
        System.out.println(">>>principal:"+principal);
        Map<String, Object> info = new HashMap<>();
        info.put(TOKEN_SEG_USER_ID, userDetails.getUserId());
        info.put(TOKEN_SEG_CLIENT, userDetails.getClientId());

        // 将用户信息添加到token额外信息中
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.getAdditionalInformation().putAll(info);

        return super.enhance(customAccessToken, authentication);
    }

    /**
     * 解析token
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        System.out.println(">>>>oauth2AccessToken:"+oauth2AccessToken.toString());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        Map<String, Object> info = new HashMap<>();
        info.put(TOKEN_SEG_USER_ID, map.get(TOKEN_SEG_USER_ID));
        info.put(TOKEN_SEG_CLIENT, map.get(TOKEN_SEG_CLIENT));
        accessToken.getAdditionalInformation().putAll(info);
    }

}
