package com.mybatis.mybatis;

import com.mybatis.mybatis.testexception.ExceptionTest;
import com.mybatis.mybatis.testexception.TemptException;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableScheduling
@EnableCaching

public class MybatisApplication {

    public static void main(String[] args) {

        SpringApplication.run(MybatisApplication.class, args);
//        new TemptException().test();

    }

//    @Bean
//    public SpringContextHolder springContextHolder() {
//        return new SpringContextHolder();
//    }



}
