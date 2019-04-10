package cn.sanleny.study.springcloud.sample.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: sanleny
 * @Date: 2019-04-10
 * @Description: cn.sanleny.study.springcloud.sample.filter
 * @Version: 1.0
 */
@Component
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.Config> {

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("withParams");
    }

    public RequestTimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        /*return ((exchange, chain) -> {
            exchange.getAttributes().put("currentTime",System.currentTimeMillis());

            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

            return chain.filter(exchange.mutate().request(builder.build()).build());
        });*/
        return (exchange, chain) -> {
            exchange.getAttributes().put("currentTime", System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute("currentTime");
                        if (startTime != null) {
                            StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                    .append(": ")
                                    .append(System.currentTimeMillis() - startTime)
                                    .append(" ms");
                            if (config.isWithParams()) {
                                sb.append(" params:").append(exchange.getRequest().getQueryParams());
                            }
                            System.out.println(">>> "+ sb.toString());
                        }
                    })
            );
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
        private boolean withParams;

        public boolean isWithParams() {
            return withParams;
        }

        public void setWithParams(boolean withParams) {
            this.withParams = withParams;
        }
    }
}
