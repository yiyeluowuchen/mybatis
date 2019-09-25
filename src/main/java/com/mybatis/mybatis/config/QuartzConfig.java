package com.mybatis.mybatis.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;
import com.mybatis.mybatis.quartz.QuartzDemo;

import javax.annotation.Resource;

@Component
@Configuration
public class QuartzConfig {


    @Resource(name = "scheduler")
    private Scheduler scheduler;


    public void add() throws Exception {
        //1.创建Job对象
        JobDetail job = JobBuilder.newJob(com.mybatis.mybatis.quartz.QuartzDemo.class).build();

        //2.创建Trigger对象，在什么时候做
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        scheduler.scheduleJob(job,trigger);
        scheduler.start();

    }




    /**
     * 1.创建job对象  做什么
     */
//    @Bean
//    public JobDetailFactoryBean jobDetailFactoryBean() {
//        JobDetailFactoryBean factory = new JobDetailFactoryBean();
//        //关联自己的job类
//        factory.setJobClass(QuartzDemo.class);
//        return factory;
//    }

    /**
     * 2.创建Trigger对象  什么时候做
     * <p>
     * 简单Trigger
     */
   /* @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        //关联JobDetail对象
        factory.setJobDetail(jobDetailFactoryBean.getObject());
        //设置触发时间 该参数表示毫秒,2000表示2秒
        factory.setRepeatInterval(2000);
        factory.set
        //设置重复次数
        factory.setRepeatCount(5);
        return factory;
    }*/

    /**
     *  Cron Trigger
     */
//    @Bean
//    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
//        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
//        //关联JobDetail
//        factory.setJobDetail(jobDetailFactoryBean.getObject());
//        //                             秒   分钟 小时 天 月 星期
//        factory.setCronExpression("0/5 * * * * ?");
//        //表示每年每月每天的11点34分的10,15,20秒都会执行一次
//        return factory;
//    }


    /**
     * 3.创建Scheduler对象
     */
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean, MyDaaptableJobFactory myDaaptableJobFactory) {
//        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//        //关联trigger
//        factoryBean.setTriggers(cronTriggerFactoryBean.getObject());
//        factoryBean.setJobFactory(myDaaptableJobFactory);
//        return factoryBean;
//    }

}
