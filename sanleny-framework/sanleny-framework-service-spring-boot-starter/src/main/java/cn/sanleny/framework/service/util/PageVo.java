package cn.sanleny.framework.service.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LG
 * @Date: 2019-05-27
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class PageVo<T> extends Page<T> {

    private T entity;

    public PageVo() {

    }

    public PageVo(T entity, long size){
        this.entity = entity;
        this.setSize(size);
    }
}
