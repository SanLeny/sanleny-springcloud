package cn.sanleny.framework.auth.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security相关操作
 * @Author: lg
 * @Date: 2019-07-04
 * @Version: 1.0
 */
public class SecurityUtils {

    /**
     * 根据当前令牌信息获取当前用户
     * @return
     */
    public static CustomUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取当前登录认证用户信息
     * @param authentication
     * @return
     */
    public static CustomUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof CustomUser) {
            return (CustomUser) principal;
        }
        return null;
    }


    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
