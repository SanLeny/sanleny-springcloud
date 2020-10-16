package cn.sanleny.study.auth.sample.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义令牌对象
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken  {

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials,authorities);
    }

}
