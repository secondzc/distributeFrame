package com.tongyuan.distributeFrame.demo.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tongyuan.distributeFrame.demo.entity.User;
import com.tongyuan.distributeFrame.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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

    @Test
    public void test1(){
        User user = new User("44","zcy1","123root");
        Long i = userService.insert(user);
        System.out.println(i);
    }

}