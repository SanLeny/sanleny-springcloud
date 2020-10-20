package cn.sanleny.demo.client.feign;

import cn.sanleny.demo.entity.User;
import cn.sanleny.frameword.core.common.util.ResultData;
import cn.sanleny.framework.service.util.PageVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LG
 * @Date: 2020-10-13
 * @Version: 1.0
 **/
@FeignClient(contextId = "userServiceClient" ,name = "demo-server"
//        ,fallback = UserServiceClientFallbackImpl.class
)
public interface UserServiceClient {

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
