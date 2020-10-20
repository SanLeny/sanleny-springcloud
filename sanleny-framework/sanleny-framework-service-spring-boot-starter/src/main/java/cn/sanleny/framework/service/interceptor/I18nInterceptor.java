package cn.sanleny.framework.service.interceptor;

import cn.sanleny.frameword.core.common.constant.CommonConstants;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化 Interceptor
 * @Author: lg
 * @Date: 2019-06-11
 * @Version: 1.0
 */
public class I18nInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String parameter = request.getHeader(CommonConstants.LOCALE);
        if("en_US".equals(parameter)){
            LocaleContextHolder.setLocale(Locale.US);
        }else{
            LocaleContextHolder.setLocale(Locale.CHINA);
        }
        return true;
    }
}
