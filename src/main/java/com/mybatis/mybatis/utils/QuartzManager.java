package com.mybatis.mybatis.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 配置job，trigger，sheduler
 */
@Configuration
public class QuartzManager {
    @Autowired
    Scheduler scheduler;
   public void add(){

       try {
          //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
           JobDetail jobDetail = JobBuilder.newJob(com.mybatis.mybatis.utils.QuartzJob.class)
                   .withIdentity("job")
                   .build();
           Trigger trigger = TriggerBuilder.newTrigger()
                   .withIdentity("trigger")
                   .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
                   .build();
           scheduler.scheduleJob(jobDetail, trigger);
           // scheduler.start();
       }catch(Exception e){
                e.printStackTrace();
       }

   }

}
