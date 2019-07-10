package cn.sanleny.study.springcloud.sample.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局拦截器，作用所有的微服务
 * @Author: sanleny
 * @Date: 2019-04-10
 * @Description: cn.sanleny.study.springcloud.sample.filter
 * @Version: 1.0
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
        log.info(token);
        if (token == null || token.isEmpty()) {
            log.info( "token is empty..." );
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    public int getOrder() {
        return 0;
    }
}
