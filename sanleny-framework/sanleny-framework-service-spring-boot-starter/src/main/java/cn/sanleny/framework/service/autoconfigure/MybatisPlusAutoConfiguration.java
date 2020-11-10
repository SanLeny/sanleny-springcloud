package cn.sanleny.framework.service.autoconfigure;

import cn.sanleny.framework.auth.common.util.SecurityUtils;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页
 * https://baomidou.com/guide/page.html
 * https://baomidou.com/guide/interceptor.html#分页插件-paginationinnerinterceptor
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
@Configuration
@ConditionalOnClass(com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer.class)
public class MybatisPlusAutoConfiguration {

    @Autowired
    private DatabaseProperties databaseProperties;

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //租户模式
        switch (databaseProperties.getTenantType() ){
            case COLUMN:
                TenantLineInnerInterceptor tenantLineInnerInterceptor =
                        new TenantLineInnerInterceptor(new TenantLineHandler() {
                            /**
                             * 获取租户 ID 值表达式，只支持单个 ID 值
                             * @return
                             */
                            @Override
                            public Expression getTenantId() {
                                return new StringValue(SecurityUtils.getUser().getTenantId());
                            }

                            /**
                             * 根据表名判断是否忽略拼接多租户条件,默认都要进行解析并拼接多租户条件
                             * @param tableName
                             * @return
                             */
                            @Override
                            public boolean ignoreTable(String tableName) {
                                return false;
                            }

                            /**
                             * 获取租户字段名
                             * @return
                             */
                            @Override
                            public String getTenantIdColumn() {
                                return databaseProperties.getTenantIdColumn();
                            }
                        });
                interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
                break;
            case NONE:
            case SCHEMA:
            case DATASOURCE:
                break;
        }

        //分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(databaseProperties.getDbType());
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        //防止全表更新与删除
        //https://baomidou.com/guide/interceptor-block-attack.html#blockattackinnerinterceptor
        if(databaseProperties.getIsBlockAttack()){
            BlockAttackInnerInterceptor baii = new BlockAttackInnerInterceptor();
            interceptor.addInnerInterceptor(baii);
        }
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
