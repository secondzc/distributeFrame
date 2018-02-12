package com.tongyuan.distributeFrame.exception;

import java.util.Map;

/**
 * Created by zhangcy on 2018/2/12
 */
public class BaseException extends RuntimeException{
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public void handler(Map<String,Object> map){
        map.put("success",false);
        map.put("errMsg",getMessage());
    }
}
