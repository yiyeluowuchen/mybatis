package com.mybatis.mybatis.scheduled;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * 基于静态
 */
@Component //表明这是一个组件，如果该类都不好归为哪种类时，可以用这个来标识
/**
 * //主要表明这是一个配置类，兼@Component的功能，
 * 和EnableXXX一起用 就是从@Configuration 启用和配置Spring等功能。
 *EnableAsync：异步方法执行
 * @EnableScheduling：计划定时任务
 * 等
 */
@Configuration  // 1.用于标志配置类，兼有@Component的功能
@EnableScheduling  //开启定时任务
public class StaticScheduleTask {

    //添加定时任务
  // @Scheduled (cron = "0/5 * * * * ?")
    //或者直接指定间隔
    //@Scheduled(fixedRate = 5000)
    private void configureTasks (){
            //返回的是毫秒数
        //  System.out.println("执行静态定时任务"+ System.currentTimeMillis());
              System.out.println("执行静态定时任务"+LocalDateTime.now());

    }



}
