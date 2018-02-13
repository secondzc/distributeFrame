package com.tongyuan.distributeFrame.base;

import com.alibaba.fastjson.JSON;
import com.tongyuan.distributeFrame.util.ContextHolderUtil;
import com.tongyuan.distributeFrame.util.InstanceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by zhangcy on 2018/2/13
 * 取出spring容器管理的bean来真正执行方法的类
 */
public class BaseProvider  {

    private Logger logger = LogManager.getLogger(BaseProvider.class);

    public Parameter execute(Parameter request){
        String serviceName = request.getClassName();
        Object service = ContextHolderUtil.getBean(serviceName);
        logger.info("{} request",JSON.toJSONString(request));
        Object result = InstanceUtil.invokeMethod(service,request.getMethodName(),request.getParams());
        logger.info("{} response",JSON.toJSONString(result));
        Parameter response = new Parameter(result);
        return response;
    }
}
