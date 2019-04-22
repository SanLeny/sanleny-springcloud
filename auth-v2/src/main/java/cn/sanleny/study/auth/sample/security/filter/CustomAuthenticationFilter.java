package cn.sanleny.study.auth.sample.security.filter;

import cn.sanleny.study.auth.sample.Util.TokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证过滤器
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.security.filter
 * @Version: 1.0
 */
public class CustomAuthenticationFilter extends BasicAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = TokenUtils.getToken(request);
        System.out.println("doFilterInternal..token>>>"+token);
        if(token !=null){

        }
        chain.doFilter(request,response);
    }
}
