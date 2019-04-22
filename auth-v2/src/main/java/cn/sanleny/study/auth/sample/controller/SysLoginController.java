package cn.sanleny.study.auth.sample.controller;

import cn.sanleny.study.auth.sample.entity.SysUser;
import cn.sanleny.study.auth.sample.security.CustomAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: sanleny
 * @Date: 2019-04-18
 * @Description: cn.sanleny.study.auth.sample.controller
 * @Version: 1.0
 */
@RestController
public class SysLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String login(@RequestBody SysUser sysUser, HttpServletRequest request){
        //TODO 这里简单进行验证,假设用户验证通过
        String username = sysUser.getName();
        String password = sysUser.getPassword();

        String userId= sysUser.getUserId();
        String clientId= sysUser.getClientId();

        CustomAuthenticationToken token = new CustomAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);



        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @PreAuthorize("hasAnyAuthority('atest:atest:atest')")
    @GetMapping("/atest")
    public String atest(){
        return "atest";
    }

    @PreAuthorize("hasAnyAuthority('list:list:list')")
    @GetMapping("/btest")
    public String btest(){
        return "btest";
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/ctest")
    public String ctest(){
        return "ctest";
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/dtest")
    public String dtest(){
        return "dtest";
    }



}
