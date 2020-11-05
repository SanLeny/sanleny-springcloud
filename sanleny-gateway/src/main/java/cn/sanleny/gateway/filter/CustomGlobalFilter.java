package cn.sanleny.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.sanleny.frameword.core.autoconfigure.PermitAllUrlProperties;
import cn.sanleny.frameword.core.common.constant.CommonConstants;
import cn.sanleny.frameword.core.common.exception.GlobalFallbackException;
import cn.sanleny.frameword.core.common.util.ResultData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局拦截器，作用所有的微服务
 * @Author: LG
 * @Date: 2020-10-09
 * @Version: 1.0
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private final PermitAllUrlProperties permitAllUrlProperties;

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("请求路径 >>> path:{}",path);
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(CommonConstants.AUTHORIZATION);
        if(permitAllUrlProperties.isIgnoreToken(path)) {
            //跳过权限验证
            return chain.filter(exchange);
        }
        if(StrUtil.isBlank(authorization)){
            ServerHttpResponse response = exchange.getResponse();
            // header set
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            try {
                return response.writeWith(Mono.just(response.bufferFactory()
                        .wrap(objectMapper.writeValueAsBytes(
                                ResultData.failed(HttpStatus.UNAUTHORIZED.value(),"token is empty ...")
                        ))));
            } catch (JsonProcessingException ex) {
                log.error("对象输出异常", ex);
                throw new GlobalFallbackException(ex.getMessage());
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
