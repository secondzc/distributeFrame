package com.tongyuan.distributeFrame.util;

import com.tongyuan.distributeFrame.cache.CacheManager;
import com.tongyuan.distributeFrame.cache.RedisHelper;

/**
 * Created by zhangcy on 2018/2/14
 */
public class CacheUtil {
    private static CacheManager cacheManager;

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }

    public static boolean trylock(String lockKey){
        return cacheManager.lock(lockKey);
    }

    public static void unlock(String lockKey){
        cacheManager.unlock(lockKey);
    }
}
