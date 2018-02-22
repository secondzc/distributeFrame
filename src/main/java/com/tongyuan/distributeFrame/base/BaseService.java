package com.tongyuan.distributeFrame.base;

/**
 * Created by zhangcy on 2018/2/20
 */
public interface BaseService<T extends BaseModel> {
    void del(Long id);
    String getCacheKey(Object id);
    String getLockKey(Object id);
    T queryById(Long id);
    T queryByIdCache(Long id);
    Boolean updateById(T record);
}
