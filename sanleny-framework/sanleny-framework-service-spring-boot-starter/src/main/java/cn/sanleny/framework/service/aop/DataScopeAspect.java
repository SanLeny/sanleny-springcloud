package cn.sanleny.framework.service.aop;

import cn.sanleny.framework.service.annotation.DataScope;
import cn.sanleny.framework.service.entity.BaseEntity;
import cn.sanleny.framework.service.util.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * 数据权限范围过滤 aop
 * @Author: LG
 * @Date: 2020-11-04
 * @Version: 1.0
 **/
@Aspect
@Component
@Slf4j
public class DataScopeAspect {

    // 定义一个全局的 Pointcut
    @Pointcut("@annotation(cn.sanleny.framework.service.annotation.DataScope) || @within(cn.sanleny.framework.service.annotation.DataScope)")
    public void dataScope(){

    }

    @Before("dataScope()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
        //获取注解
        DataScope dataScope = method.getAnnotation(DataScope.class);
        Object[] args = joinPoint.getArgs();
        if(args == null) {
            return;
        }
        //获取注解对应要设置数据权限过滤范围的对象
        Optional<Object> optional = Arrays.stream(args)
                .filter(x -> dataScope.aClass().isInstance(x)).findFirst();
        if(!optional.isPresent()){
            log.warn(">>>没有符合的参数！不能进行权限过滤");
            return;
        }
        //设置数据权限范围过滤
        Object object = optional.get();
        if(object instanceof BaseEntity){
            ((BaseEntity) object).setDataScopeStatus(true);
        } else if(object instanceof PageVo && ((PageVo) object).getEntity() instanceof BaseEntity){
            ((BaseEntity)((PageVo) object).getEntity()).setDataScopeStatus(true);
        }
    }

}
