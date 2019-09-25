package com.mybatis.mybatis.quartz;

import com.mybatis.mybatis.service.IUerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * 1.先写一个执行任务的方法 ，通过继承，这里面调用serviece里面的方法。
 * 2.写job对象，来做什么，
 * 3.写trigger对象，什么时候触发，
 * 4.写一个解决注入为空的类
 */

/*public class QuartzDemo  implements Job {
    @Autowired
   private  IUerService iUerService;
    /**
     * 任务触发时，被执行的方法
     * @param context
     * @throws JobExecutionException
     */
    /*@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("Excute-----"+ new Date());

        iUerService.insertUser();
    }
}*/

public class QuartzDemo  extends QuartzJobBean {

    @Autowired
    private  IUerService iUerService;


    @Resource(name = "scheduler")
    private Scheduler scheduler;

    /**
     * 创建了一个触发时，执行的方法。
     * @param context
     * @throws JobExecutionException
     */

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


        System.out.println("Excute-----"+ new Date());

        iUerService.insertUser();

    }
}