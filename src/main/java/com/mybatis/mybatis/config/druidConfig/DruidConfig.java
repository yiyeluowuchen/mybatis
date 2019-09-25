package com.mybatis.mybatis.config.druidConfig;//package com.mybatis.mybatis.config.druidConfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

   @ConfigurationProperties(prefix = "spring.datasource")//这是把yml中那些属性配置连接进来使用 如这种：initialSize: 5,注意路径在这些属性的上一级
    @Bean
    public DataSource druid(){//间接父类
        return new DruidDataSource();//该类的默认构造方法
    }

    //配置Druid的监控
    //配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams = new HashMap<>();
        //在ResourceServlet中能找到下面的loginUsername，loginPassord这些信息，这是StateViewServlet的子类
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
       // initParams.put("deny","192.168.15.21");

        bean.setInitParameters(initParams);
        return bean;
    }
    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");//WebStatFileter中有相关信息

        bean.setInitParameters(initParams);

       bean.setUrlPatterns(Arrays.asList("/*"));//这个不知道有啥用

        return  bean;
    }

}
