package cn.sanleny.framework.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: LG
 * @Date: 2020-09-09
 * @Version: 1.0
 **/
public class SuperEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected String id;

    /**
     * 租户 ID
     */
    @Getter
    @Setter
    protected String tenantId;

}
