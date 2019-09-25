package com.mybatis.mybatis.config;

import com.mybatis.mybatis.utils.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 主程序启动时，该代码也会启动
 */
@Configuration
public class JobRunner implements ApplicationRunner {

    @Autowired
    QuartzManager quartzManager;
    @Override
    public void run(ApplicationArguments args) throws Exception {
    //定时备份数据库数据
        //quartzManager.add();
    }
}
