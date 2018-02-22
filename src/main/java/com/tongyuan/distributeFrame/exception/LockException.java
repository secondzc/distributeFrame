package com.tongyuan.distributeFrame.exception;

/**
 * Created by zhangcy on 2018/2/22
 */
public class LockException extends BaseException {
    public LockException() {
    }

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockException(Throwable cause) {
        super(cause);
    }
}
