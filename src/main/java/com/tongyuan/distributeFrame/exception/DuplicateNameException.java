package com.tongyuan.distributeFrame.exception;

/**
 * Created by zhangcy on 2018/2/26
 */
public class DuplicateNameException extends BaseException{

    public DuplicateNameException() {
    }

    public DuplicateNameException(String message) {
        super(message);
    }

    public DuplicateNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateNameException(Throwable cause) {
        super(cause);
    }
}
