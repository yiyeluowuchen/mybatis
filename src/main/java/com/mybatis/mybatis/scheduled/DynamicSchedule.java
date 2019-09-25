//package com.mybatis.mybatis.scheduled;
//
//import com.mybatis.mybatis.mapper.ISocksMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.time.LocalDateTime;
//
//@Component
//@Configuration
//@EnableScheduling
//public class DynamicSchedule  implements SchedulingConfigurer {
//
//    @Autowired
//    ISocksMapper iSocksMapper;
//
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//
//        taskRegistrar.addTriggerTask(
//                //1.添加任务内容(Runnable)
//                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now()),
//                //2.设置执行周期(Trigger)
//                triggerContext -> {
//                    //2.1 从数据库获取执行周期
//                    String cron = iSocksMapper.selectCron(1);  //这里返回的是String类型，故不能返回实体类，不然报类型转换错误。
//                    //2.2 合法性校验.
//                    if (StringUtils.isEmpty(cron)) {
//                        // Omitted Code ..
//                    }
//                    //2.3 返回执行周期(Date)
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
//
//    }
//}
