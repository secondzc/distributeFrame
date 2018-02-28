package com.tongyuan.distributeFrame.demo.controller;



import com.tongyuan.distributeFrame.demo.entity.User;
import com.tongyuan.distributeFrame.util.CacheUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangcy on 2018/2/27
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private CacheManager cacheManager;


    @ResponseBody
    @PostMapping("/cache")
    public void testCache(){
        com.tongyuan.distributeFrame.cache.springRedis.CacheManager cacheManager1 = CacheUtil.getCacheManager();
        Cache zhangCache = cacheManager.getCache("zhang");
        zhangCache.put("1","this test value");
        String v = (String) zhangCache.get("1");
        System.out.println(v);
    }

    @ResponseBody
    @GetMapping("/rememberme")
    public void testRememberme(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
    }
}
