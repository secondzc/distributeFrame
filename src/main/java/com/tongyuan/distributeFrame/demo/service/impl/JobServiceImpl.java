package com.tongyuan.distributeFrame.demo.service.impl;

import com.tongyuan.distributeFrame.demo.scheduler.HelloWorldJob;
import com.tongyuan.distributeFrame.demo.service.JobService;
import com.tongyuan.distributeFrame.quartz.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/3/1
 */
@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private QuartzService quartzService;

    @Override
    public void test1() {
        Class jobClass = HelloWorldJob.class;
        String jobName = "job1";
        String jobGroup = "jobGroup1";
        String triggerName = "trigger1";
        String triggerGroup = "triggerGroup1";
        //每10s执行一次
        quartzService.addJob(jobName,jobGroup,triggerName,triggerGroup,jobClass,"0/10 * * * * ?");
        //一分钟之后停止
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        quartzService.pauseJob(jobName,jobGroup);
        quartzService.shutdownSchedule();
    }
}
