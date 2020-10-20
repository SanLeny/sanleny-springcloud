package cn.sanleny.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sanleny.demo.dao.UserMapper;
import cn.sanleny.demo.entity.User;
import cn.sanleny.demo.service.UserService;
import cn.sanleny.framework.service.util.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageVo dataGrid(PageVo pageVo, User user) {
        return userMapper.dataGrid(pageVo,user);
    }
}
