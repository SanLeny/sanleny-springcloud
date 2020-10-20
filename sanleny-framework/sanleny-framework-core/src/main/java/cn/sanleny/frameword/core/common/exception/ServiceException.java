package cn.sanleny.frameword.core.common.exception;

/**
 * 业务类异常
 * @Author: LG
 * @Date: 2020-09-10
 * @Version: 1.0
 **/
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
