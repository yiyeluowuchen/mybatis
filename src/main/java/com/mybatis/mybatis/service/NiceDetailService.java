package com.mybatis.mybatis.service;


import com.mybatis.mybatis.entity.NiceDetail;
import com.mybatis.mybatis.mapper.mapper1.NiceDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NiceDetailService {

    /**
     * 查找该用户是否有点赞，就是否有记录在数据库
     */

    @Autowired
    NiceDetailMapper niceDetailMapper;
    public NiceDetail find(NiceDetail niceDetail){

        return niceDetailMapper.find(niceDetail);
    }

    /**
     * 有点赞后，插入数据
     */

    public void insert(NiceDetail niceDetail){
        niceDetailMapper.insert(niceDetail);
    }

    /**
     * 取消点赞后，删除记录
     */

    public void delete(NiceDetail niceDetail){

        niceDetailMapper.delete(niceDetail);
    }
}
