package com.tongyuan.distributeFrame.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongyuan.distributeFrame.base.BaseServiceImpl;
import com.tongyuan.distributeFrame.base.PageProcess;
import com.tongyuan.distributeFrame.demo.dao.UserMapper;
import com.tongyuan.distributeFrame.demo.entity.User;
import com.tongyuan.distributeFrame.demo.service.UserService;
import com.tongyuan.distributeFrame.exception.DuplicateNameException;
import com.tongyuan.distributeFrame.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by zhangcy on 2018/2/15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> queryUserPageByName(Map<String, Object> map) {
        return new PageProcess<User>(){
            @Override
            public List<User> doquery(Map<String, Object> map) {
                return userMapper.queryByName(map);
            }
        }.getPageInfo(map);
    }

    /*
    若注册名称重复，则抛异常
    返回主键
     */
    @Override
    public Long insert(User user) {
        String username = user.getUsername();
        User queryUser = queryByUsername(username);
        if(queryUser != null){
            throw new DuplicateNameException("已存在同名用户！");
        }else {
            userMapper.insert(user);
            return user.getId();
        }
    }

    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

}
