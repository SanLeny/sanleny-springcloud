package cn.sanleny.study.auth.sample.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义LogoutHandler  清除token
 * @Author: sanleny
 * @Date: 2019-04-19
 * @Description: cn.sanleny.study.auth.sample.security.filter
 * @Version: 1.0
 */
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("Authorization");
        log.info(">>>token:"+token);
        if(StringUtils.isNotBlank(token)){
            //清除token
            OAuth2AccessToken existingAccessToken = tokenStore.readAccessToken(token);
            if(existingAccessToken !=null){
                if(existingAccessToken.getRefreshToken() !=null){
                    log.info("remove refreshToken!", existingAccessToken.getRefreshToken());
                    OAuth2RefreshToken refreshToken = existingAccessToken.getRefreshToken();
                    tokenStore.removeRefreshToken(refreshToken);
                }
                log.info("remove existingAccessToken!", existingAccessToken);
                tokenStore.removeAccessToken(existingAccessToken);
            }
        }else{
            throw new BadClientCredentialsException();
        }


    }
}
