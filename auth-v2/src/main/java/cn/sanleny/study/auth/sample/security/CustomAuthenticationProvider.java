package cn.sanleny.study.auth.sample.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义身份验证者
 * @Author: sanleny
 * @Date: 2019-04-19
 * @Description: cn.sanleny.study.auth.sample.security.filter
 * @Version: 1.0
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final String name = "sanleny";
    private final String pwd = "123456";

    /**
     * 验证过程
     * 如果AuthenticationProvider返回了null，AuthenticationManager会交给下一个支持authentication类型的AuthenticationProvider处理
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //TODO 这里验证用户，查询数据库或者缓存得到权限列表
        if(this.isMatch(authentication)){
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
//            Map data = (Map) authentication.getDetails();
//            String clientId = (String) data.get("clientId");
//            String userId = (String) data.get("userId");
            String clientId ="1";
            String userId = "1";
            // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
            //        Set<String> permissions = sysUserService.findPermissions(user.getName());
            Set<String> permissions = new HashSet<>();
            permissions.add("add:add:add");
            permissions.add("edit:edit:edit");
            /**
             * 1. 放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
             * 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
             * @see org.springframework.security.access.expression.SecurityExpressionRoot#hasAnyRole
             */
            permissions.add("ROLE_USER");//设置角色
            List<GrantedAuthority> grantedAuthorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("add:add:add", "edit:edit:edit", "ROLE_USER");
            CustomUserDetails customUserDetails = new CustomUserDetails(username,password,userId,clientId,grantedAuthorities);
            CustomAuthenticationToken authenticationToken =
                    new CustomAuthenticationToken(customUserDetails, authentication.getCredentials(), grantedAuthorities);
            authenticationToken.setDetails(authentication.getDetails());
            return authenticationToken;
        }else{
            throw new BadCredentialsException("授权失败");
        }
//        return null;
    }

    /**
     * support方法检查authentication的类型是不是这个AuthenticationProvider支持的，
     * 这里我简单地返回true，就是所有都支持，这里所说的authentication为什么会有多个类型，是因为多个AuthenticationProvider可以返回不同的Authentication
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    private boolean isMatch(Authentication authentication){
        if(authentication.getName().equals(name) && authentication.getCredentials().equals(pwd))
            return true;
        else
            return false;
    }
}
