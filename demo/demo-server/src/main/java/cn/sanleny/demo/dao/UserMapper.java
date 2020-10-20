package cn.sanleny.demo.dao;

import cn.sanleny.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.sanleny.framework.service.util.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    PageVo<User> dataGrid(PageVo pageVo, @Param("entity") User user);
}
