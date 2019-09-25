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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 配置mapper1数据源
 */
@Configuration
@MapperScan(basePackages = {"com.mybatis.mybatis.mapper.mapper1"}, sqlSessionFactoryRef = "mapper1SqlSessionFactory")
public class Mapper1Config {
    private static final String MAPPER_LOCAL = "classpath:mapping/*/*.xml";

    /**
     * 配置mapper1数据库
     * @return
     */
    @ConfigurationProperties("spring.datasource.mapper1")
    @Bean(name = "mapper1DataSource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置事物管理
     * @return
     */

    @Bean(name = "mapper1TransactionManager")
    //@ConditionalOnMissingBean(PlatformTransactionManager.class)  这个用来手动配置事务管理器，springboot就不会给我们自动配置事务管理器，但这里没有起作用
    public DataSourceTransactionManager mapper1TransactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }

    /**
     * 配置sql会话工厂
     * @param dataSource
     * @return
     * @throws Exception
     */

    @Bean(name = "mapper1SqlSessionFactory")
    public SqlSessionFactory mapper1SqlSessionFactory(@Qualifier("mapper1DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCAL));
        return sessionFactoryBean.getObject();
    }



}
