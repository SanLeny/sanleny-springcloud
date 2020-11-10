package cn.sanleny.framework.service.autoconfigure;

import cn.sanleny.framework.service.constant.TenantType;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库配置
 * @Author: LG
 * @Date: 2020-11-09
 * @Version: 1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = DatabaseProperties.PREFIX)
public class DatabaseProperties {

    public static final String PREFIX = "sanleny.database";

    /**
     * 是否启用数据权限
     */
    private Boolean isDataScope = true;
    /**
     * 数据库类型
     */
    public DbType dbType = DbType.MYSQL;
    /**
     * 是否启用 防止全表更新与删除插件
     * @return
     */
    private Boolean isBlockAttack = false;

    /**
     * 多租户模式
     */
    private TenantType tenantType = TenantType.COLUMN;
    /**
     * 租户id 列名
     */
    private String tenantIdColumn = "tenant_id";

}
