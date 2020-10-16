package cn.sanleny.study.auth.sample.Util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: sanleny
 * @Date: 2019-04-20
 * @Description: cn.sanleny.study.auth.sample.Util
 * @Version: 1.0
 */
public class TokenUtils {

    /**
     * 获取请求token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if(token == null) {
            token = request.getHeader("token");
        }
        if("".equals(token)) {
            token = null;
        }
        return token;
    }

}
