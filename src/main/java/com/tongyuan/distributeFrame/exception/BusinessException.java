package com.tongyuan.distributeFrame.exception;

/**
 * Created by zhangcy on 2018/2/12
 */
public class BusinessException extends BaseException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
