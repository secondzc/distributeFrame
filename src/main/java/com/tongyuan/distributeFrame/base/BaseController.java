package com.tongyuan.distributeFrame.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageInfo;
import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.exception.BaseException;
import com.tongyuan.distributeFrame.exception.IllegalParameterException;
import com.tongyuan.distributeFrame.util.HttpUtil;
import com.tongyuan.distributeFrame.util.WebUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    protected Map<String,Object> setResponse(Object result){
        Map<String,Object> map = new HashMap<String,Object>();
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
            map.put("pageSize",pageInfo.getPageSize());
            //总页数
            map.put("pages",pageInfo.getPages());
            //当前页的目录
            map.put("pageNum",pageInfo.getPageNum());
        }else{
            map.put("data",result);
        }
        return map;
    }

    protected String getPara(String name) {
        return HttpUtil.getRequest().getParameter(name);
    }

    /**
     * pageNum 页数
     * numPerPage 每页显示的数目
     */
    protected  Page getPage() {
        int _numPerPage = 20, _pageNum = 1;
        if (getPara("pageNum") != null) {
            _pageNum = Integer.parseInt(getPara("pageNum"));
        }
        if (getPara("numPerPage") != null) {
            _numPerPage = Integer.parseInt(getPara("numPerPage"));
        }
        return new Page(_pageNum, _numPerPage);
    }
}

