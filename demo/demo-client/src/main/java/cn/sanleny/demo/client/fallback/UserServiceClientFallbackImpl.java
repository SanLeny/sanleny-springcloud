package cn.sanleny.demo.client.fallback;

import cn.sanleny.demo.client.feign.UserServiceClient;
import cn.sanleny.demo.entity.User;
import cn.sanleny.frameword.core.common.util.ResultData;
import cn.sanleny.framework.service.util.PageVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: LG
 * @Date: 2020-10-13
 * @Version: 1.0
 **/
@Slf4j
@Component
public class UserServiceClientFallbackImpl
        implements UserServiceClient
{
    @Setter
    private Throwable cause;

    public ResultData dataGrid(PageVo pageVo) {
        log.error("feign 查询信息失败:{}", pageVo, cause);
        return ResultData.failed(cause);
    }

    public ResultData dataGrid1(PageVo<User> pageVo) {
        log.error("feign 查询信息失败:{}", pageVo, cause);
        return ResultData.failed(cause);
    }

    public ResultData dataList(User user) {
        log.error("feign 查询列表信息失败:{}", user, cause);
        return ResultData.failed(cause);
    }

    public ResultData saveOrUpdate(User user) {
        log.error("feign 新增或保存信息失败:{}", user, cause);
        return ResultData.failed(cause);
    }

    public ResultData save(User user) {
        log.error("feign 保存信息失败:{}", user, cause);
        return ResultData.failed(cause);
    }

    public ResultData update(User user) {
        log.error("feign 更新信息失败:{}", user, cause);
        return ResultData.failed(cause);
    }

    public ResultData delete(String id) {
        log.error("feign 删除失败:{}", id, cause);
        return ResultData.failed(cause);
    }
}
