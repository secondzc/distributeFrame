package com.tongyuan.distributeFrame.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/12
 */
public class BaseService<T extends BaseModel> {

    protected PageInfo<T> queryPages(Map<String,Object> params,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<T> models = queryCallback(params);
        PageInfo<T> pageInfo = new PageInfo<T>(models);
        return pageInfo;
    }

    //父类的空实现，使用时根据业务需要传值
    protected  List<T> queryCallback(Map<String,Object> params){
        return null;
    }
}
