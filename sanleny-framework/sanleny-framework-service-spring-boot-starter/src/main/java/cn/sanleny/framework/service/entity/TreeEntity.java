package cn.sanleny.framework.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description: 树形结构
 * @author: JS
 * @create: 2020-09-14 15:54
 **/
@Data
@Accessors(chain = true)
public class TreeEntity<T> extends BaseEntity<T> {


    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;


    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<T> children = new ArrayList<>();

    /**
     * 是否最末层 (0否 1是)
     */
    private String leaf;

    /**
     * 层次级别
     */
    private BigDecimal level;

    /**
     * 父级名称
     */
    @TableField(exist = false)
    private String parentName;

}
