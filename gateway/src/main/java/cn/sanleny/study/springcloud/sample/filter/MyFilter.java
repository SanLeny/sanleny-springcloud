package cn.sanleny.study.springcloud.sample.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: sanleny
 * @Date: 2019-04-10
 * @Description: cn.sanleny.study.springcloud.sample.filter
 * @Version: 1.0
 */
@Component
public class MyFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
//        if(CorsUtils.isCorsRequest(request)){
            System.out.println(">>>== "+request.getQueryParams());
            System.out.println(">>>== "+request.getId());
            System.out.println(">>>== "+request.getCookies());
            System.out.println(">>>== "+request.getRemoteAddress());
            System.out.println(">>>== "+request.getSslInfo());
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
