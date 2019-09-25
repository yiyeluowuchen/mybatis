package com.mybatis.mybatis.utils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.util.Date;

/**
 * 创建定时任务类
 */
public class QuartzJob  extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println("进入任务类"+new Date());

        String filePath = "C:\\Users\\zhangle\\Desktop\\何连辉\\copy";
        String service = "localhost";
        String dbName = "mybatis";
        String username = "root";
        String password = "zhang";
        File  uploadDir = new File(filePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        /**
         * cmd备份数据库的语句，
         * root：数据库用户名
         * password：密码
         * dbName ：要备份的数据库的名字
         * filePath：备份到哪的名字
         * 注意这下面一些空格
         */
       // MySQLdump -uroot -pzhang -h localhost mybatis > C:\Users\zhangle\Desktop\何连辉\copy\back.sql
        String cmd ="MySQLdump -u"+username +" -p"+password+" -h" +" "+service+" "+ dbName +" -r"
                + filePath + "/" + dbName+new java.util.Date().getTime()+ ".sql";
                System.out.println(cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);//管理员运行
            System.out.println("备份数据库成功!!!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


}
