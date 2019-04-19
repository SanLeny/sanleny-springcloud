package cn.sanleny.study.auth.sample.security;

import cn.sanleny.study.auth.sample.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义用户认证Service
 * @Author: sanleny
 * @Date: 2019-04-19
 * @Description: cn.sanleny.study.auth.sample.security
 * @Version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里验证用户信息
        //TODO
        //SysUser user = sysUserService.findByName(username);
        SysUser user =new SysUser();
        user.setName(username);
        user.setPassword("123456");
        user.setSalt("234");

        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
//        Set<String> permissions = sysUserService.findPermissions(user.getName());
        Set<String> permissions = new HashSet<>();
        permissions.add("list:list:list");
        permissions.add("edit:edit:edit");
        /**
         * 1. 放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
         * 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
         * @see org.springframework.security.access.expression.SecurityExpressionRoot#hasAnyRole
         */
        permissions.add("ROLE_USER");//设置权限和角色
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}
