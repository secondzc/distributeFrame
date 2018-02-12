package com.tongyuan.distributeFrame.handler;

import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.exception.BaseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/12
 */
public class GlobalExcpetionHandler {

    private Logger logger = LogManager.getLogger(GlobalExcpetionHandler.class);

    /** 异常处理 */
    @ExceptionHandler(Exception.class)
    public Map<String,Object> exceptionHandler(Exception ex)
            throws Exception {
        logger.error(Constants.EXCPETION_HEAD, ex);
        Map<String,Object> map = new HashMap<>();
        if (ex instanceof BaseException) {
            ((BaseException) ex).handler(map);
        }else{
            map.put("success",false);
            map.put("errMsg","发生了未定义错误");
        }
        return map;
    }
}
