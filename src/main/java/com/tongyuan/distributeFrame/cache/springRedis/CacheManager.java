package com.tongyuan.distributeFrame.cache.springRedis;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangcy on 2018/2/13
 *
 */


public interface CacheManager {
    Object get(final String key);

    Set<Object> getAll(final String pattern);

    void set(final String key, final Serializable value, int seconds);

    void set(final String key, final Serializable value);

    Boolean exists(final String key);

    void del(final String key);

    void delAll(final String pattern);

    String type(final String key);

    Boolean expire(final String key, final int seconds);

    Boolean expireAt(final String key, final long unixTime);

    Long ttl(final String key);

    Object getSet(final String key, final Serializable value);

    boolean lock(String key);

    void unlock(String key);

    void hset(String key, Serializable field, Serializable value);

    Object hget(String key, Serializable field);

    void hdel(String key, Serializable field);

    //若不存在则添加
    boolean setnx(String key, Serializable value);

    Long incr(String key);

    void setrange(String key, long offset, String value);

    String getrange(String key, long startOffset, long endOffset);

    void sadd(String key, Serializable value);

    Set<?> sall(String key);

    boolean sdel(String key, Serializable value);

    /*
    对list的操作
     */
    void laddAll(String key,List<?> value);

    List<?> lgetrange(String key, long startOffset, long endOffset);

    List<?> lgetAll(String key);

}