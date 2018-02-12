package com.tongyuan.distributeFrame.exception;

/**
 * Created by zhangcy on 2018/2/12
 */
public class IllegalParameterException extends BaseException {
    public IllegalParameterException() {
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }
}
