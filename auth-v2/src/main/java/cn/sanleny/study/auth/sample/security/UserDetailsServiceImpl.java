package cn.sanleny.study.auth.sample.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义用户认证Service
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String name = "sanleny";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里验证用户信息
        System.out.println(">>>> loadUserByUsername:"+username);
        //TODO
        //SysUser user = sysUserService.findByName(username);
        if(!name.equals(username)){
            throw  new  UsernameNotFoundException("username:"+username +" not found");
        }
        String password="123456";
        String clientId ="1";
        String userId = "1";

        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
//        Set<String> permissions = sysUserService.findPermissions(user.getName());
        /**
         * 1. 放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
         * 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
         * @see org.springframework.security.access.expression.SecurityExpressionRoot#hasAnyRole
         */
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("add:add:add", "edit:edit:edit", "ROLE_USER");
        return new CustomUserDetails(username,password,userId,clientId,grantedAuthorities);
    }


}
