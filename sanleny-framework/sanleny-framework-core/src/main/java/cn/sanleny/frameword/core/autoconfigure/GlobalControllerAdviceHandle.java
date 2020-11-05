package cn.sanleny.frameword.core.autoconfigure;

import cn.hutool.json.JSONUtil;
import cn.sanleny.frameword.core.common.exception.GlobalFallbackException;
import cn.sanleny.frameword.core.common.util.ResultData;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @Author: liguang5
 * @Date: 2019-05-31
 * @Description: com.lenovo.lemes.framework.core.exception
 * @Version: 1.0
 */
@Log4j2
@RestControllerAdvice
public class GlobalControllerAdviceHandle {

    @ExceptionHandler(HystrixRuntimeException.class)
    public ResultData hystrixRuntimeException(HystrixRuntimeException exception) {
        Throwable cause = exception.getCause();
        log.error(">>> {}",cause.getMessage(),cause);
        return ResultData.failed(cause.getMessage());
    }

    @ExceptionHandler(GlobalFallbackException.class)
    public ResultData globalFallbackExceptionException(GlobalFallbackException exception) {
        Throwable cause = exception.getCause();
        log.error(">>> {}",cause.getMessage(),cause);
        ResultData resultData = JSONUtil.toBean(exception.getData(), ResultData.class);
        resultData.setMsg(resultData.getMsg() + " " + exception.getMessage());
        return resultData;
    }

}
