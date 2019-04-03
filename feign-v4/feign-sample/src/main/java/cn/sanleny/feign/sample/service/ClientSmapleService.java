package cn.sanleny.feign.sample.service;

import cn.sanleny.feign.sample.fallback.ClientSmapleFallback;
import cn.sanleny.feign.sample.model.Teacher;
import feign.Body;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: sanleny
 * @Date: 2019-04-03
 * @Description: cn.sanleny.feign.sample.service
 * @Version: 1.0
 */
@Component
@FeignClient(name = "client",fallback = ClientSmapleFallback.class)
public interface ClientSmapleService {

    @GetMapping("/teacher")
    Teacher getTeacher();

    @GetMapping("/user")
    String getByTeacher(@RequestParam("id") String id);

    @Body("%7B\"orderNo\":\"{orderNo}\"%7D")
    @PostMapping("/user")
    String getByTeacher1(@Param("orderNo") String orderNo);

}
