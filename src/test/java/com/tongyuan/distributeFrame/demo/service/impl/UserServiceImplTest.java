package com.tongyuan.distributeFrame.demo.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tongyuan.distributeFrame.cache.shiro.RedisCache;
import com.tongyuan.distributeFrame.demo.entity.User;
import com.tongyuan.distributeFrame.demo.service.UserService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangcy on 2018/2/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BaseMapper baseMapper;
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void test1(){
        User user = new User("44","zcy1","123root");
        Long i = userService.insert(user);
        System.out.println(i);
    }

    @Test
    public void test2(){
        Cache cache = cacheManager.getCache("cad");
        RedisCache redisCache = (RedisCache)cache;
        redisCache.put("zz",1);
        System.out.println("更改前" + redisCache.ttl("zz"));
        redisCache.setExpire("zz",300);
        System.out.println("更改后" + redisCache.ttl("zz"));
    }


}