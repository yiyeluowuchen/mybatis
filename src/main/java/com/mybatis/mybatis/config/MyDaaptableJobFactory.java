package com.mybatis.mybatis.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class MyDaaptableJobFactory extends AdaptableJobFactory {

    //AutowireCapableBeanFactory 可以将一个对象添加到Spring的IOC容器中,并且完成该对象的注入
    @Autowired
    private AutowireCapableBeanFactory autowiredCaptableBeanFactory;
    /**
     *  该方法需要将实例化的任务对象手动的添加到Spring的IOC容器中,并且完成对象的注入
     *
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object o = super.createJobInstance(bundle);
        //将o对象添加到springIOC容器中,并完成注入
        autowiredCaptableBeanFactory.autowireBean(o);
        return o;
    }

}

