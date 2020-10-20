package cn.sanleny.demo.service;

import cn.sanleny.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.sanleny.framework.service.util.PageVo;

/**
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
public interface UserService extends IService<User> {

    PageVo dataGrid(PageVo pageVo, User query);

}
