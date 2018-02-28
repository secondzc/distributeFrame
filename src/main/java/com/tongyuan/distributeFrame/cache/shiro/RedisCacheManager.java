package com.tongyuan.distributeFrame.cache.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangcy on 2018/2/27
 */
public class RedisCacheManager implements CacheManager{

    private final ConcurrentHashMap<String,Cache> caches = new ConcurrentHashMap<String,Cache>();

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = caches.get(name);
        if(cache == null){
            cache = new RedisCache();
            caches.put(name,cache);
        }
        return cache;
    }
}
