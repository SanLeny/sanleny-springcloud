package cn.sanleny.framework.service.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
@Data
@Accessors(chain = true)
public class BaseEntity<T> extends SuperEntity<T> {

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    protected LocalDateTime createTime;//创建时间
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    protected String createUser; //创建人

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;//最后修改时间
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    protected String updateUser; //最后修改人

}
