package cn.sanleny.study.auth.sample.security;
import org.springframework.security.core.GrantedAuthority;

/**
 * 授权权限模型封装
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}