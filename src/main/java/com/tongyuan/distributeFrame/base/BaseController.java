package com.tongyuan.distributeFrame.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageInfo;
import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.exception.BaseException;
import com.tongyuan.distributeFrame.exception.IllegalParameterException;
import com.tongyuan.distributeFrame.util.HttpUtil;
import com.tongyuan.distributeFrame.util.InstanceUtil;
import com.tongyuan.distributeFrame.util.WebUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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

    protected Map<String,Object> setSuccessResponse(Object result){
        Map<String,Object> map = new InstanceUtil().newHashMap();
        if(result instanceof List<?>){
            map.put("total",((List<?>) result).size());
            map.put("rows",result);
        }else if(result instanceof PageInfo<?>){
            PageInfo<?> pageInfo = (PageInfo<?>) result;
            //总记录数
            map.put("total",pageInfo.getTotal());
            //结果集
            map.put("rows",pageInfo.getList());
            //每页的数量
            map.put("pageSize",pageInfo.getSize());
            //总页数
            map.put("pages",pageInfo.getPages());
            //当前页的目录
            map.put("current",pageInfo.getPageNum());
        }else{
            map.put("data",result);
        }
        map.put("code",200);
        map.put("flag",true);
        return map;
    }

    protected Map<String,Object> setErrorResponse(Integer code,String msg){
        Map<String,Object> map = new InstanceUtil().newHashMap();
        map.put("code",code);
        map.put("errMsg",msg);
        map.put("flag",false);
        return map;
    }

    protected Map<String,Object> setErrorResponse(String msg){
        Map<String,Object> map = new InstanceUtil().newHashMap();
        map.put("code",200);
        map.put("errMsg",msg);
        map.put("flag",false);
        return map;
    }

    protected Map<String,Object> setErrorResponse(Integer code,Throwable throwable){
        Map<String,Object> map = new InstanceUtil().newHashMap();
        map.put("code",code);
        map.put("flag",false);
        map.put("errMsg",throwable.getMessage());
        return map;
    }

    protected Map<String,Object> setErrorResponse(Throwable throwable){
        Map<String,Object> map = new InstanceUtil().newHashMap();
        map.put("code",200);
        map.put("flag",false);
        map.put("errMsg",throwable.getMessage());
        return map;
    }

    protected String getPara(String name) {
        return HttpUtil.getRequest().getParameter(name);
    }

    /**
     * @param param  前端传入的@RequestBody
     * @param mappingRule  前后端不一致的键的映射表(key=前端，value=后端）
     * @return  传给service层的map
     */
    public Map<String,Object> wrap(Map<String,Object> param,Map<String,String> mappingRule){
        for(Map.Entry<String,String> rule:mappingRule.entrySet()){
            String front = rule.getKey();
            String back = rule.getValue();
            Object data = param.get(front);
            param.put(back,data);
        }
        return param;
    }
}

