package com.tongyuan.testmp1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tongyuan.testmp1.dao.UserMapper;
import com.tongyuan.testmp1.entity.User;
import com.tongyuan.testmp1.service.UserService;
import org.springframework.stereotype.Service;


/**
 * Created by zhangcy on 2018/2/15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
