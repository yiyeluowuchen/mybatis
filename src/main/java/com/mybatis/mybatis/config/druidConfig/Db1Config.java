package com.mybatis.mybatis.config.druidConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 读取db1数据源
 */

@Configuration
@MapperScan(basePackages = {"com.mybatis.mybatis.mapper.db1"}, sqlSessionFactoryRef = "db1SqlSessionFactory")

public class Db1Config {

    private static final String MAPPER_LOCAL = "classpath:mapping/pri_mapping/*.xml";//这里pri_mapping改成*就是匹配所有，也可以

    /**
     * 配置下db1数据库
     * @return
     */

    @ConfigurationProperties("spring.datasource.db2")//这里db2是在yml中配置的db2跟mapper没有关系
    @Primary//主数据源
    @Bean(name = "db1DataSource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置该数据源的事务管理
     * @return
     */

    //@ConditionalOnMissingBean(PlatformTransactionManager.class)这个用来手动配置事务管理器，springboot就不会给我们自动配置事务管理器，但这里没有起作用
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager db1TransactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }

    /**
     * 配置sql会话工厂
     * @param dataSource
     * @return
     * @throws Exception
     */

    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //找到该sql语句中位置
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
        return sessionFactoryBean.getObject();
    }

}
