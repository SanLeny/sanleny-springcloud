package cn.sanleny.framework.service.interceptor;

import cn.sanleny.framework.service.entity.BaseEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

/**
 * mybatis 数据范围权限拦截器
 * <p></p>
 * https://baomidou.com/guide/interceptor.html#mybatisplusinterceptor
 * 1：全部  2：个人  3：本部门  4 本部门及以下   5自定义
 * @Author: LG
 * @Date: 2020-10-27
 * @Version: 1.0
 **/
@Slf4j
@Component
public class DataScopeInnerInterceptor implements InnerInterceptor {

    /**
     * 查找参数是否包括 BaseEntity 对象
     *
     * @param parameterObj 参数列表
     * @return BaseEntity
     */
    protected Optional<BaseEntity> findDataScope(Object parameterObj) {
        if (parameterObj == null) {
            return Optional.empty();
        }
        if (parameterObj instanceof BaseEntity) {
            return Optional.of((BaseEntity) parameterObj);
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof Wrapper) {
                    Object entity = ((Wrapper) val).getEntity();
                    if(null != entity){
                        return Optional.of((BaseEntity)entity );
                    }
                } else if(val instanceof BaseEntity){
                    return Optional.of((BaseEntity)val );
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        BaseEntity baseEntity = findDataScope(parameter).orElse(null);
        if (baseEntity == null || !baseEntity.isDataScopeStatus()) {
            return;
        }
//        SysUser sysUser = UserUtil.getUser();
//        if(sysUser.hasAdmin()) {
//            //超级管理员，直接跳过
//            return;
//        }
//        // 进行权限过滤，多个角色权限范围之间为或者关系。
//        //用户角色
//        SysRoleService sysRoleService = SpringContextHolder.getBean(SysRoleService.class);
//        List<SysRole> roles = sysRoleService.findRoles(sysUser);
//        boolean isDataScopeAll = roles.stream().parallel()
//                .anyMatch(x -> StrUtil.equals(DataScopeType.ALL.toString(),x.getDataScope()));
//        //全部
//        if(isDataScopeAll) {
//            return;
//        }
//        boolean isDataScopeSelf = roles.stream().parallel()
//                .allMatch(x -> StrUtil.equals(DataScopeType.SELF.toString(),x.getDataScope()));
//        String originalSql = boundSql.getSql();
//        //个人
//        if(isDataScopeSelf) {
//            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope.create_user = " + sysUser.getId();
//        } else {
//            String officeId = sysUser.getSysUserInfo().getOfficeId();
//            //其它
//            Set<String> officeIds = new HashSet<>();
//            //本部门
//            boolean isDataScopeThisLevel = roles.stream().parallel()
//                    .filter(x -> StrUtil.equals(DataScopeType.THIS_LEVEL.toString(),x.getDataScope())
//                        ).findAny().isPresent();
//            if(isDataScopeThisLevel) {
//                officeIds.add(officeId);
//            }
//            //本部门及以下
//            boolean isDataScopeThisLevelChildren = roles.stream().parallel()
//                    .filter(x -> StrUtil.equals(DataScopeType.THIS_LEVEL_CHILDREN.toString(),x.getDataScope())
//                        ).findAny().isPresent();
//            if(isDataScopeThisLevelChildren) {
//                SysOfficeService officeService = SpringContextHolder.getBean(SysOfficeService.class);
//                SysOffice office = new SysOffice();
//                office.setParentId(officeId);
//                office.setStatus(CommonConstants.STATUS_NORMAL);
//                //当前用户的下级部门
//                List<SysOffice> offices = officeService.selectList(office);
//                office.setId(officeId);
//                offices.add(office);
//                officeIds.addAll(offices.stream().map(SuperEntity::getId).collect(Collectors.toSet()));
//            }
//            //自定义
//            List<SysRole> sysRoles = roles.stream().parallel()
//            .filter(x -> StrUtil.equals(DataScopeType.CUSTOMIZE.toString(),x.getDataScope()))
//            .collect(Collectors.toList());
//            for (SysRole sysRole : sysRoles) {
//                List<RoleOffice> roleOffices = sysRoleService.getRoleOffice(sysRole);
//                officeIds.addAll(roleOffices.stream().map(RoleOffice::getOfficeId).collect(Collectors.toSet()));
//            }
//            originalSql = "select temp_data_scope.* from (" + originalSql + ") temp_data_scope " +
//                    ",sys_user_info temp_data_scope_u " +
//                    " where temp_data_scope_u.id = temp_data_scope.create_user " +
//                    " and temp_data_scope_u.office_id in ("+
//                    officeIds.stream().collect(Collectors.joining("','","'","'"))
//                    +")";
//
//        }
//        PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
//        mpBoundSql.sql(originalSql);
    }
}
