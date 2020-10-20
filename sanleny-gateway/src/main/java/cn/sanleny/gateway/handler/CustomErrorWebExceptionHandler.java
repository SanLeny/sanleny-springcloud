package cn.sanleny.gateway.handler;

import cn.sanleny.frameword.core.common.exception.GlobalFallbackException;
import cn.sanleny.frameword.core.common.util.ResultData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义网关异常 Handler
 * @Author: liguang
 * @Date: 2019-05-09
 * @Version: 1.0
 */
@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class CustomErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorMessage;
        int errorCode = 500;
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException e = (ResponseStatusException) ex;
            errorMessage = e.getMessage();
            errorCode = e.getStatus().value();
            response.setStatusCode((e.getStatus()));
        } else {
            errorMessage = ex.getMessage();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            return response.writeWith(Mono.just(response.bufferFactory()
            .wrap(objectMapper.writeValueAsBytes(ResultData.failed(errorCode,errorMessage)))));
        } catch (JsonProcessingException e) {
            log.error("对象输出异常", e);
            throw new GlobalFallbackException(ex.getMessage());
        }
    }
}
