package com.tongyuan.distributeFrame.exception;

/**
 * Created by zhangcy on 2018/2/14
 * 与预期不相符的结果集数量异常
 */

public class UnexpectedRowNumException extends BaseException{
    public UnexpectedRowNumException() {
    }

    public UnexpectedRowNumException(String message) {
        super(message);
    }

    public UnexpectedRowNumException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedRowNumException(Throwable cause) {
        super(cause);
    }
}
