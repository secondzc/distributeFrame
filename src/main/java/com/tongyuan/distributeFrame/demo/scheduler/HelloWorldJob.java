package com.tongyuan.distributeFrame.demo.scheduler;

import com.tongyuan.distributeFrame.util.FormatUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by zhangcy on 2018/3/1
 */
public class HelloWorldJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("===hello world====" + FormatUtil.date2String(new Date()));
    }
}
