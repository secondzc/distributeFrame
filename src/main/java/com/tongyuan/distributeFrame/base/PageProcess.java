package com.tongyuan.distributeFrame.base;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongyuan.distributeFrame.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/22
 */
public abstract class PageProcess<T extends BaseModel> {
    public abstract List<T> doquery(Map<String,Object> map);

    public PageInfo<T> getPageInfo(Map<String,Object> map){
        int current = Integer.parseInt(map.get("current").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
//        String orderBy = map.get("orderBy").toString();
        PageHelper.startPage(current, pageSize);
        List<T> records = doquery(map);
        PageInfo<T> pageInfo = new PageInfo<T>(records);
        return pageInfo;
    }

}
