package cn.sanleny.framework.service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 多租户类型
 * @Author: LG
 * @Date: 2020-10-29
 * @Version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum TenantType {

    NONE("非租户模式"),
    /**
     * 字段模式
     * 在sql中拼接 tenant_code 字段
     */
    COLUMN("字段模式"),
    /**
     * 独立schema模式
     * 在sql中拼接 数据库 schema
     */
    SCHEMA("独立schema模式"),
    /**
     * 独立数据源模式
     * <p>
     * 该模式不开源，购买咨询作者。
     */
    DATASOURCE("独立数据源模式"),
    ;

    private String desc;

    public static TenantType match(String value, TenantType def) {
        return Stream.of(values()).parallel().filter((item) -> item.name().equalsIgnoreCase(value)).findAny().orElse(def);
    }

    public static TenantType get(String code) {
        return match(code, null);
    }

}
