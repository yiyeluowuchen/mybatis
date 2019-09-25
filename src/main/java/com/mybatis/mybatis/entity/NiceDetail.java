package com.mybatis.mybatis.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 粗略做一个点赞的功能
 */

@Data
public class NiceDetail {

    /**
     * 自增id
     */
    private long id;
    /**
     *  用户id
     */
    private  long userId;
    /**
     * 内容id
     */

    private long contentId;
    /**
     * 点赞时间
     */

    private Timestamp  createTime;



}
