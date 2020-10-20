package cn.sanleny.auth.controller;

import cn.sanleny.demo.client.feign.TestServiceClient;
import cn.sanleny.demo.entity.User;
import cn.sanleny.frameword.core.common.util.ResultData;
import cn.sanleny.framework.service.util.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LG
 * @Date: 2020-09-28
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestServiceClient testServiceClient;

    /**
     * 跳过权限测试
     * @param pageVo
     * @param user
     * @return
     */
    @GetMapping("/atest")
    public ResultData atest(PageVo<User> pageVo, User user){
        pageVo.setEntity(user);
        System.out.println("atest");
        return testServiceClient.dataGrid(pageVo);
    }

    /**
     * 异常测试
     * @param pageVo
     * @return
     */
    @PreAuthorize("hasAuthority('edit:edit:edit')")
    @GetMapping("/btest")
    public ResultData btest(PageVo<User> pageVo){
        System.out.println("btest");
        return testServiceClient.dataGrid1(pageVo);
    }

    /**
     * 正常测试
     * @return
     */
    @GetMapping("/ctest")
    @PreAuthorize("hasAuthority('list:list:list')")
    public ResultData ctest(){
        System.out.println("ctest");
        return testServiceClient.dataList(new User());
    }

    @GetMapping("/ctest1")
    @PreAuthorize("hasAuthority('list:list:list1')")
    public ResultData ctest1(){
        System.out.println("ctest1");
        return ResultData.succeed("ctest1");
    }

    @GetMapping("/dtest")
    @PreAuthorize("hasRole('USER')")
    public ResultData dtest(){
        System.out.println("dtest");
        return ResultData.succeed("dtest");
    }

    @GetMapping("/dtest1")
    @PreAuthorize("hasRole('USER1')")
    public ResultData dtest1(){
        System.out.println("dtest1");
        return ResultData.succeed("dtest1");
    }

}
