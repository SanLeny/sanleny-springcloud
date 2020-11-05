package cn.sanleny.framework.service.autoconfigure;

import cn.sanleny.frameword.core.common.constant.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: liguang5
 * @Date: 2019-06-12
 * @Version: 1.0
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                if(CommonConstants.AUTHORIZATION.equalsIgnoreCase(name) ){
                    requestTemplate.header(name, values);
                }
                if(CommonConstants.LOCALE.equalsIgnoreCase(name)){
                    requestTemplate.header(name, values);
                }
            }
        }
    }
}
