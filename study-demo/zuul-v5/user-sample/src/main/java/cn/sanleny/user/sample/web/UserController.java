package cn.sanleny.user.sample.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: sanleny
 * @Date: 2019-04-17
 * @Description: cn.sanleny.user.sample.web
 * @Version: 1.0
 */
@RestController
@RequestMapping("/testuser")
public class UserController {

    @GetMapping("/getUser")
    public String getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        if(StringUtils.isEmpty(username)){
            session.setAttribute("username", "testSessionRedis|" + System.currentTimeMillis());
        }
        return username;
    }




}
