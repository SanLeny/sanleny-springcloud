package cn.sanleny.demo.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import cn.sanleny.framework.service.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@TableName(value = "user") 不写这个的话，默认实体类名对应为表名
public class User extends BaseEntity<User> {

    @TableField(condition = SqlCondition.LIKE)
    private String name;
    private Integer age;
    private String email;

    /**
     * 0-正常，1-删除
     */
    @TableLogic
    private String delFlag;

}
