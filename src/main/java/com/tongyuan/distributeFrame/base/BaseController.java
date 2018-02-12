package com.tongyuan.distributeFrame.base;

import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.exception.BaseException;
import com.tongyuan.distributeFrame.exception.IllegalParameterException;
import com.tongyuan.distributeFrame.util.WebUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/12
 */
public class BaseController {
    private Logger logger = LogManager.getLogger(BaseController.class);

    /** 获取当前用户Id */
    protected Long getCurrUser() {
        return WebUtil.getCurrentUser();
    }

}

