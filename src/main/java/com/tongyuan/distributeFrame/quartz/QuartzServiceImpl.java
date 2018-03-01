package com.tongyuan.distributeFrame.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcy on 2018/3/1
 */
public class QuartzServiceImpl implements QuartzService{
    private final Logger logger = LogManager.getLogger();

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls, String cron) {
        try {
            //创建一项作业
            JobDetail jobDetail = JobBuilder.newJob(cls)
                    .withIdentity(jobName,jobGroupName).build();
            //创建一个触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName,triggerGroupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            //调度
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            logger.error("添加定时任务失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modifyJobTime(String oldjobName, String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
        try{
            TriggerKey triggerKey = TriggerKey.triggerKey(oldtriggerName,oldtriggerGroup);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(trigger == null){
                return false;
            }

            JobKey jobKey = JobKey.jobKey(oldjobName,oldjobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            Class jobClass = jobDetail.getJobClass();

            //移除任务
            removeJob(jobName,jobGroup,triggerName,triggerGroup);
            //重建任务
            addJob(jobName,jobGroup,triggerName,triggerGroup,jobClass,cron);
            return true;
        }catch(Exception e){
            logger.error("修改任务失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        try{
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(TriggerKey.triggerKey(triggerName,triggerGroupName));
            if(cronTrigger == null){
                return ;
            }
            String oldTime = cronTrigger.getCronExpression();
            if(!oldTime.equalsIgnoreCase(cron)){
                //修改时间
                cronTrigger.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
                //重启触发器
                scheduler.resumeTrigger(TriggerKey.triggerKey(triggerName,triggerGroupName));
            }
        }catch(Exception e){
            logger.error("修改任务失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        try{
            scheduler.pauseJob(JobKey.jobKey(jobName,jobGroupName));
        }catch(Exception e){
            logger.error("暂停任务失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resumeJob(String jobName, String jobGroupName) {
        try{
            scheduler.resumeJob(JobKey.jobKey(jobName,jobGroupName));
        }catch(Exception e){
            logger.error("重启任务失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try{
            // 停止触发器
            scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName,
                    triggerGroupName));
            // 移除触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName,
                    triggerGroupName));
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        }catch(Exception e){
            logger.error("移除任务失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startSchedule() {
        try{
            scheduler.start();
        }catch(Exception e){
            logger.error("开启调度器失败");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdownSchedule() {
        try{
            if(!scheduler.isShutdown()){
                scheduler.shutdown();
            }
        }catch(Exception e){
            logger.error("关闭调度器失败");
            throw new RuntimeException(e);
        }
    }
}
