package com.mybatis.mybatis.entity;

/**
 * 定时器，动态基于接口
 */

public class Socks {

    private Integer cron_id;

    private String cron;

    public Integer getCron_id() {
        return cron_id;
    }


    public void setCron_id(Integer cron_id) {
        this.cron_id = cron_id;
    }

//    public Integer getCronId() {
//        return cronId;
//    }
//
//    public void setCronId(Integer cronId) {
//        this.cronId = cronId;
//    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
