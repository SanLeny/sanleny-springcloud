package cn.sanleny.frameword.core.autoconfigure;

import cn.sanleny.frameword.core.common.exception.GlobalFallbackException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import static feign.FeignException.errorStatus;

/**
 * 自定义 Feign 异常处理
 * @Author: liguang
 * @Date: 2019-05-31
 * @Version: 1.0
 */
@Configuration
@ConditionalOnClass(feign.codec.ErrorDecoder.class)
public class DefaultErrorDecoder
        implements ErrorDecoder
{
    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = errorStatus(methodKey, response);
        return new GlobalFallbackException(exception.status(),exception.contentUTF8(),exception);
    }
}
