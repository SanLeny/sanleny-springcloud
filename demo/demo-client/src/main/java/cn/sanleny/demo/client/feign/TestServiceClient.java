package cn.sanleny.demo.client.feign;

import cn.sanleny.demo.client.factory.TestServiceClientFallbackFactory;
import cn.sanleny.demo.entity.User;
import cn.sanleny.frameword.core.common.util.ResultData;
import cn.sanleny.framework.service.util.PageVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * fallback 和 fallbackFactory 两注解用其一就好，同时存在时只有fallback生效
 * fallbackFactory 方式，可以处理异常信息
 * @Author: LG
 * @Date: 2020-10-13
 * @Version: 1.0
 **/
@FeignClient(contextId = "testServiceClient" ,name = "demo-server",path = "/test"
//        ,fallback = TestServiceClientFallbackImpl.class
        ,fallbackFactory = TestServiceClientFallbackFactory.class
)
public interface TestServiceClient {

    /**
     * 使用mybatis-plus 自带分页
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "/atest")
    ResultData dataGrid(@RequestBody PageVo<User> pageVo);

    /**
     * 使用自定义分页
     * @param pageVo
     * @return
     */
    @RequestMapping(value = "/dataGrid1")
    ResultData dataGrid1(@RequestBody PageVo<User> pageVo);

    /**
     * 查询列表 不传参数为 {} 即查询所有
     * @param user
     * @return
     */
    @RequestMapping(value = "/dataList")
    ResultData dataList(@RequestBody User user);

    /**
     * TableId 注解存在更新记录，否插入一条记录
     * id 为空或不存在 即新增 , 否则为修改
     * @param user
     * @return
     */
    @PostMapping("/saveOrUpdate")
    ResultData saveOrUpdate(@RequestBody User user);

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/save")
    ResultData save(@RequestBody User user);

    /**
     * 根据 ID 选择修改
     * @param user
     * @return
     */
    @PostMapping("/update")
    ResultData update(@RequestBody User user);

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    ResultData delete(@PathVariable("id") String id);

}
