package cn.sanleny.study.springcloud.sample.filter;

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
public class MyGlobalFilter implements GlobalFilter, Ordered {

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
        System.out.println(token);
        if (token == null || token.isEmpty()) {
            System.out.println( "token is empty..." );
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    public int getOrder() {
        return 0;
    }
}
