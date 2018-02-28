package com.tongyuan.distributeFrame.cache.shiro;

import com.tongyuan.distributeFrame.constant.Constants;
import com.tongyuan.distributeFrame.util.CacheUtil;
import com.tongyuan.distributeFrame.util.DataUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zhangcy on 2018/2/27
 */
public class RedisCache<K,V> implements Cache<K,V>{
    private final Logger logger = LogManager.getLogger();

    private static final String keyPrefix = Constants.CACHE_NAMESPACE + "shiro_redis_session:";

    //单位：秒
    public void setExpire(K key , Integer expire) {
        CacheUtil.getCacheManager().expire(getKey(key),expire);
    }

    public Long ttl(K key){
        return CacheUtil.getCacheManager().ttl(getKey(key));
    }

    private String getKey(K key){
        return keyPrefix + key;
    }

    @Override
    public V get(K k) throws CacheException {
        logger.info("从redis中获取对象 key= [{}]",k);
        V value = (V) CacheUtil.getCacheManager().get(getKey(k));
        return value;
    }

    /*
    返回value
     */
    @Override
    public V put(K k, V v) throws CacheException {
        logger.info("向redis中存储 [{},{}]",k,v);
        CacheUtil.getCacheManager().set(getKey(k),(Serializable) v);
        return v;
    }

    /*
    return：旧的value
     */
    @Override
    public V remove(K k) throws CacheException {
        logger.info("从redis中移除 key = [{}]",k);
        V old = get(k);
        CacheUtil.getCacheManager().del(getKey(k));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        logger.info("从redis中移除所有元素");
        CacheUtil.getCacheManager().delAll(keyPrefix + "*");
    }

    @Override
    public int size() {
        return CacheUtil.getCacheManager().getAll(keyPrefix + "*").size();
    }

    /*
    集合泛型一个一个转
     */
    @Override
    public Set<K> keys() {
        Set<Object> keys = CacheUtil.getCacheManager().getAll(this.keyPrefix + "*");
        if (DataUtil.isEmpty(keys)) {
            return Collections.emptySet();
        } else {
            Set<K> newKeys = new HashSet<K>();
            for (Object key : keys) {
                newKeys.add((K)key);
            }
            return newKeys;
        }
    }

    /*
    return: value的view
     */
    @Override
    public Collection<V> values() {
        Set<Object> keys = CacheUtil.getCacheManager().getAll(this.keyPrefix + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            List<V> values = new ArrayList<V>(keys.size());
            for (Object key : keys) {
                @SuppressWarnings("unchecked")
                V value = get((K)key);
                if (value != null) {
                    values.add(value);
                }
            }
            return Collections.unmodifiableList(values);
        } else {
            return Collections.emptyList();
        }
    }
}
