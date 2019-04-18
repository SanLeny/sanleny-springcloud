package cn.sanleny.feign.sample.fallback;

import cn.sanleny.feign.sample.model.Teacher;
import cn.sanleny.feign.sample.service.ClientSmapleService;
import org.springframework.stereotype.Component;

/**
 * 定义降级方法，这里直接去实现我们定义的feign接口
 * @Author: sanleny
 * @Date: 2019-04-03
 * @Description: cn.sanleny.feign.sample.fallback
 * @Version: 1.0
 */
@Component
public class ClientSmapleFallback implements ClientSmapleService {
    @Override
    public Teacher getTeacher() {
        return new Teacher("错误",-1,"0","错误降级");
    }

    @Override
    public String getByTeacher(String id) {
        return "降级了_"+id;
    }

    @Override
    public String getByTeacher1(String orderNo) {
        return "降级了1_"+orderNo;
    }

    @Override
    public String getUser() {
        return "降级了_";
    }
}
