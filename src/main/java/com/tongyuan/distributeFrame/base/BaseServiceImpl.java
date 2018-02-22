package com.tongyuan.distributeFrame.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.demo.entity.User;
import com.tongyuan.distributeFrame.exception.LockException;
import com.tongyuan.distributeFrame.util.CacheUtil;
import com.tongyuan.distributeFrame.util.DataUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
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
    public String getCacheKey(Object id){
        String prefix = getCacheKey();
        return new StringBuilder(prefix).append(":").append(id).toString();
    }

    @Override
    public String getLockKey(Object id){
        String prefix = getCacheKey();
        return new StringBuilder(prefix).append(":LOCK:").append(id).toString();
    }

    private String getCacheKey(){
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
    使用缓存查询 若缓存命中，则直接读取，否则，从数据库中读取，最后写入缓存
     */
    @Override
    public T queryByIdCache(Long id){
        T record = null;
        try {
            String cacheKey = getCacheKey(id);
            record = (T) CacheUtil.getCacheManager().get(cacheKey);
            if(DataUtil.isNotEmpty(record)){
                logger.info("缓存命中，key: {} record:{}",cacheKey,record);
            } else {
                record = baseMapper.selectById(id);
                CacheUtil.getCacheManager().set(cacheKey,record);
                logger.info("将以下查询结果添加到缓存：key:{} record:{}",cacheKey,record);
            }
        } catch (Exception e) {
            logger.error(Constants.EXCPETION_HEAD,e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
        return record;
    }

    /*
    更新操作：先加锁，更新mysql，再更新缓存
    高并发下对数据库的update操作会被mysql串行执行，应该使用内存锁，将压力从mysql中转移
     */
    @Transactional
    protected Boolean updateById(T record,int times){
        Long id = record.getId();
        String lockKey = getLockKey(id);
        if(CacheUtil.trylock(lockKey)){
            try {
                Integer effectedRows = baseMapper.updateById(record);
                //更新数据库成功则更新缓存
                if(effectedRows.equals(1)){
                    String cacheKey = getCacheKey(id);
                    CacheUtil.getCacheManager().set(cacheKey,record);
                    logger.info("将以下查询结果添加到缓存：key:{} record:{}",cacheKey,record);
                }
            } finally {
                CacheUtil.unlock(lockKey);
            }
        }else{
            if(times>3){
                logger.info("缓存加锁失败,更新失败");
                return false;
            }else{
                sleep(20L);
                return updateById(record,times+1);
            }
        }
        return true;
    }

    @Override
    public Boolean updateById(T record){
        return updateById(record,1);
    }

    /*
    random防止出现活锁
     */
    public void sleep(Long mills){
        try {
            Thread.sleep(RandomUtils.nextLong(0,mills));
        } catch (InterruptedException e) {
            logger.error("",e);
        }
    }

    /*
     删除
     */
    @Override
    @Transactional
    public void del(Long id){
        try{
            Integer effectedRows = baseMapper.deleteById(id);
            logger.info("删除成功 id={}",id);
            if(effectedRows.equals(1)){
                String cacheKey = getCacheKey(id);
                CacheUtil.getCacheManager().del(cacheKey);
                logger.info("删除缓存，key={}",cacheKey);
            }
        }catch(Exception e){
            logger.error(Constants.EXCPETION_HEAD,e.getMessage());
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /*
    查询时不使用缓存
     */
    @Override
    public T queryById(Long id){
        T record = baseMapper.selectById(id);
        return record;
    }

}
