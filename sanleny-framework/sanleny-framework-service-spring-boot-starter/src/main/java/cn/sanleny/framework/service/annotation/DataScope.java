package cn.sanleny.framework.service.annotation;


import cn.sanleny.framework.service.entity.BaseEntity;

import java.lang.annotation.*;

/**
 * 数据权限范围过滤
 * <p></p>
 * 作用于方法上
 * @Author: LG
 * @Date: 2020-11-04
 * @Version: 1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataScope {

    /**
     * 默认需要设置数据权限范围过滤的类
     * 目前只支持2种：
     * cn.sanleny.framework.service.entity.BaseEntity
     * <p></p>
     * cn.sanleny.framework.service.util.PageVo
     * @return
     */
    Class<?> aClass() default BaseEntity.class;

}
