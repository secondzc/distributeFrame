package com.tongyuan.distributeFrame.demo.service.impl;

import com.tongyuan.distributeFrame.demo.dao.UserExterdMapper;
import com.tongyuan.distributeFrame.demo.service.UserExtendService;
import com.tongyuan.distributeFrame.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by zhangcy on 2018/2/26
 */
@Service
public class UserExtendServiceImpl implements UserExtendService{
    @Autowired
    private UserExterdMapper userExterdMapper;

    @Override
    public Set<String> getRoles(String username) {
        return CollectionUtil.list2Set(userExterdMapper.getRoles(username));
    }

    @Override
    public Set<String> getPermissions(String username) {
        return CollectionUtil.list2Set(userExterdMapper.getPermissions(username));
    }
}
