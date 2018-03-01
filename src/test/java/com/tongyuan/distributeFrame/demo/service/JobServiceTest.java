package com.tongyuan.distributeFrame.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zhangcy on 2018/3/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {
    @Autowired
    private JobService jobService;

    @Test
    public void test1() throws Exception {
        jobService.test1();
    }

}