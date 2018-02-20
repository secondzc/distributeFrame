package com.tongyuan.distributeFrame.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.util.CacheUtil;
import com.tongyuan.distributeFrame.util.DataUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangcy on 2018/2/12
 * 继承基类后可配置CacheConfig(cacheNames="")
 *
 */
@Service
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    private Logger logger = LogManager.getLogger(BaseServiceImpl.class);

    @Autowired
    protected BaseMapper<T> baseMapper;

    @Override
    public String test1(){
        return "zhang";
    }

    @Override
    public String getCacheKey(Object id){
        String prefix = getCacheKey();
        return new StringBuilder(prefix).append(":").append(id).toString();
    }

    @Override
    public String getLockKey(Object id){
        String prefix = getCacheKey();
        return new StringBuilder(prefix).append(":LOCK:").append(id).toString();
    }

    @Override
    public String getCacheKey(){
        Class<?> clz = getClass();
        String cacheName = Constants.cacheKeyMap.get(clz);
        if(!DataUtil.isNotEmpty(cacheName)){
            CacheConfig cacheConfig = clz.getAnnotation(CacheConfig.class);
            if (cacheConfig != null && ArrayUtils.isNotEmpty(cacheConfig.cacheNames())) {
                cacheName = cacheConfig.cacheNames()[0];
            } else {
                cacheName = getClass().getName();
            }
            Constants.cacheKeyMap.put(clz, cacheName);
        }
        return cacheName;
    }

    /*
    使用缓存查询
     */
    @Override
    public T queryById(Long id){
        return queryById(id,1);
    }

    /*
    若缓存命中，则直接读取，否则，尝试对缓存加锁（尝试3次，若失败则不更新缓存）从数据库中读取，最后写入缓存
     */
    private T queryById(Long id,int times){
        String cacheKey = getCacheKey(id);
        T record = (T) CacheUtil.getCacheManager().get(cacheKey);
        if(DataUtil.isNotEmpty(record)){
            logger.info("key: {} 缓存命中，record = {}",cacheKey,record);
        }else{
            String lockKey = getLockKey(cacheKey);
            if(CacheUtil.trylock(lockKey)){
                try{
                    record = baseMapper.selectById(id);
                    CacheUtil.getCacheManager().set(cacheKey,record);
                    logger.info("key: {} 将以下查询结果添加到缓存：{}",cacheKey,record);
                }finally{
                    CacheUtil.unlock(lockKey);
                }
            }else{
                if(times>3){
                    return baseMapper.selectById(id);
                }
                return queryById(id,times+1);
            }
        }
        return record;
    }

    /*
    更新包括更新数据库和缓存
    redis内存锁不仅保证缓存数据一致，还能防止高并发下对数据库的并发修改
     */
    @Override
    @Transactional
    public T update(T record){
        try{
            return null;
            //todo
        }catch(Exception e){
            logger.error(Constants.EXCPETION_HEAD,e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /*
    从逻辑上删除
     */
    @Override
    @Transactional
    public void del(Long id){
        try{
            baseMapper.deleteById(id);
            //todo
        }catch(Exception e){
            logger.error(Constants.EXCPETION_HEAD,e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
