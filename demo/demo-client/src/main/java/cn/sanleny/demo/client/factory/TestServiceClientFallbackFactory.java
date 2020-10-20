package cn.sanleny.demo.client.factory;

import cn.sanleny.demo.client.fallback.TestServiceClientFallbackImpl;
import cn.sanleny.demo.client.feign.TestServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: LG
 * @Date: 2020-10-16
 * @Version: 1.0
 **/
@Component
public class TestServiceClientFallbackFactory implements FallbackFactory<TestServiceClient> {

    @Override
    public TestServiceClient create(Throwable throwable) {
        TestServiceClientFallbackImpl clientFallback = new TestServiceClientFallbackImpl();
        clientFallback.setCause(throwable);
        return clientFallback;
    }
}
