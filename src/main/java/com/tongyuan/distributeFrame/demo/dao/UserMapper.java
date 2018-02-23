package com.tongyuan.distributeFrame.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tongyuan.distributeFrame.demo.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/15
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> queryByName(Map<String,Object> map);
}
