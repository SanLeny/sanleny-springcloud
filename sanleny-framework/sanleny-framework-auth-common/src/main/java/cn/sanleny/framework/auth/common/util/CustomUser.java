package cn.sanleny.framework.auth.common.util;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 自定义安全用户模型
 * @Author: LG
 * @Date: 2020-09-19
 * @Version: 1.0
 **/
@Data
public class CustomUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;
    private String password;
    private boolean enabled = true; //账号是否可用
    private boolean accountNonExpired = true; //用户名是否没有过期
    private boolean credentialsNonExpired = true; //用户密码是否没有过期
    private boolean accountNonLocked = true; //用户名是否没有锁定

    //权限列表,可使用AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_ADMIN")返回字符串权限集合
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
     *
     * @param username the username presented to the
     * <code>DaoAuthenticationProvider</code>
     * @param password the password that should be presented to the
     * <code>DaoAuthenticationProvider</code>
     * @param enabled set to <code>true</code> if the user is enabled   账号是否可用
     * @param accountNonExpired set to <code>true</code> if the account has not expired  用户名是否没有过期
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not 用户密码是否没有过期
     * expired
     * @param accountNonLocked set to <code>true</code> if the account is not locked 用户名是否没有锁定
     * @param authorities the authorities that should be granted to the caller if they
     * presented the correct username and password and the user is enabled. Not null.
     *
     * @throws IllegalArgumentException if a <code>null</code> value was passed either as
     * a parameter or as an element in the <code>GrantedAuthority</code> collection
     */
    public CustomUser(String id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
