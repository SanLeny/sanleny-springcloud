package cn.sanleny.auth.security;

import cn.sanleny.framework.auth.common.util.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: LG
 * @Date: 2020-09-19
 * @Version: 1.0
 **/
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    /**
     * 用户密码登录
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里验证用户信息
        String id = "1";
        String password = "123456";
        password = "{bcrypt}" + new BCryptPasswordEncoder().encode(password);

        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = new HashSet<>();
        permissions.add("list:list:list");
        permissions.add("edit:edit:edit");
        permissions.add("ROLE_USER");//设置权限和角色
        /**
         * 1. 放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
         * 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
         * @see org.springframework.security.access.expression.SecurityExpressionRoot#hasAnyRole
         */
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new CustomUser(id,username,password,true,true,true,true,grantedAuthorities);
    }
}
