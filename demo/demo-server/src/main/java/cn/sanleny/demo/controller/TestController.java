package cn.sanleny.demo.controller;

import cn.sanleny.demo.entity.User;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.sanleny.demo.service.UserService;
import cn.sanleny.frameword.core.boot.autoconfigure.LangUtil;
import cn.sanleny.frameword.core.common.util.ResultData;
import cn.sanleny.framework.service.util.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * user 增删改查
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private UserService userService;

    /**
     * 使用mybatis-plus 自带分页
     * @param pageVo
     * @return
     */
    @RequestMapping("/atest")
    public ResultData dataGrid(@RequestBody PageVo<User> pageVo){
        userService.page(pageVo, Wrappers.query(pageVo.getEntity()));
        log.info(">>> 国际化测试：{}", LangUtil.get("sys_test"));
        return ResultData.succeed(pageVo);
    }

    /**
     * 使用自定义分页
     * @param pageVo
     * @return
     */
    @RequestMapping("/dataGrid1")
    public ResultData dataGrid1(@RequestBody PageVo<User> pageVo){
        userService.dataGrid(pageVo, pageVo.getEntity());
        return ResultData.succeed(pageVo);
    }


    /**
     * 查询列表 不传参数为 {} 即查询所有
     * @param user
     * @return
     */
    @RequestMapping("/dataList")
    public ResultData dataList(@RequestBody User user){
        List<User> list = userService.list(Wrappers.query(user));
//        List<User> list = userService.list();  //查询所有
        return ResultData.succeed(list);
    }

    /**
     * TableId 注解存在更新记录，否插入一条记录
     * id 为空或不存在 即新增 , 否则为修改
     * @param user
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public ResultData saveOrUpdate(@RequestBody User user){
        userService.saveOrUpdate(user);
        return ResultData.succeed();
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/save")
    public ResultData save(@RequestBody User user){
        userService.save(user);
        return ResultData.succeed();
    }

    /**
     * 根据 ID 选择修改
     * @param user
     * @return
     */
    @PostMapping("/update")
    public ResultData update(@RequestBody User user){
        userService.updateById(user);
        return ResultData.succeed();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultData delete(@PathVariable("id") String id){
        boolean flag = userService.removeById(id);
        if(!flag){
            return ResultData.failed("删除失败");
        }
        return ResultData.succeed();
    }


}
