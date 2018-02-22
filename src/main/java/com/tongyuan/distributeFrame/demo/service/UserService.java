package com.tongyuan.distributeFrame.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.tongyuan.distributeFrame.base.BaseService;
import com.tongyuan.distributeFrame.demo.entity.User;

/**
 * Created by zhangcy on 2018/2/15
 * 用了框架提供的查、更新、删除方法，其他如果有缓存操作可以参考testmp项目用@Cacheable和@CacheEvict
 */
public interface UserService extends BaseService<User> {
}
