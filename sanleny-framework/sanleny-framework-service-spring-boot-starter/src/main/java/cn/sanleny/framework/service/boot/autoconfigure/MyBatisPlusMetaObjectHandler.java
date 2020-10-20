package cn.sanleny.framework.service.boot.autoconfigure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import cn.sanleny.framework.auth.common.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * https://baomidou.com/guide/auto-fill-metainfo.html
 * mybatis-plus自动填充
 * @Author: LG
 * @Date: 2020-09-24
 * @Version: 1.0
 **/
@Configuration
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        String userId = SecurityUtils.getUser().getId();
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "createUser", String.class, userId);
        this.strictInsertFill(metaObject, "updateUser", String.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateUser", () -> SecurityUtils.getUser().getId(), String.class);
    }

}
