package cn.sanleny.study.auth.sample.security;

import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.UUID;

/**
 * 自定义的AuthorizationTokenServices
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
@Data
public class CustomAuthorizationTokenServices implements AuthorizationServerTokenServices {

    private boolean supportRefreshToken = false;

    private boolean reuseRefreshToken = true;

    private TokenStore tokenStore;

    private ClientDetailsService clientDetailsService;

    private TokenEnhancer tokenEnhancer;
    /**
     * 创建token
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {

        OAuth2AccessToken existingAccessToken = tokenStore.getAccessToken(authentication);
        OAuth2RefreshToken refreshToken;
        if (existingAccessToken != null) {
            if (existingAccessToken.getRefreshToken() != null) {
                refreshToken = existingAccessToken.getRefreshToken();
                tokenStore.removeRefreshToken(refreshToken);
            }
            tokenStore.removeAccessToken(existingAccessToken);
        }
        //recreate a refreshToken
        refreshToken = this.createRefreshToken(authentication);

        OAuth2AccessToken accessToken = this.createAccessToken(authentication, refreshToken);
        if (accessToken != null) {
            tokenStore.storeAccessToken(accessToken, authentication);
        }
        refreshToken = accessToken.getRefreshToken();
        if (refreshToken != null) {
            tokenStore.storeRefreshToken(refreshToken, authentication);
        }
        return accessToken;

    }

    private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication, OAuth2RefreshToken refreshToken) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
//        int validitySeconds = getAccessTokenValiditySeconds(authentication.getOAuth2Request());
//        if (validitySeconds > 0) {
//            token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
//        }
        token.setRefreshToken(refreshToken);
        token.setScope(authentication.getOAuth2Request().getScope());

        return tokenEnhancer != null ? tokenEnhancer.enhance(token, authentication) : token;

    }

    private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication) {
        if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
            return null;
        }
//        int validitySeconds = getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
        String value = UUID.randomUUID().toString();
//        if (validitySeconds > 0) {
//            return new DefaultExpiringOAuth2RefreshToken(value, new Date(System.currentTimeMillis()
//                    + (validitySeconds * 1000L)));
//        }
        return new DefaultOAuth2RefreshToken(value);
    }

    public boolean isSupportRefreshToken(OAuth2Request oAuth2Request) {
        if (clientDetailsService != null) {
            ClientDetails client = clientDetailsService.loadClientByClientId(oAuth2Request.getClientId());
            return client.getAuthorizedGrantTypes().contains("refresh_token");
        }
        return this.supportRefreshToken;
    }

    /**
     * 刷新token
     * @param refreshToken
     * @param tokenRequest
     * @return
     * @throws AuthenticationException
     */
    @Override
    public OAuth2AccessToken refreshAccessToken(String refreshToken, TokenRequest tokenRequest) throws AuthenticationException {
        return null;
    }

    /**
     * 获取token
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        return null;
    }

}
