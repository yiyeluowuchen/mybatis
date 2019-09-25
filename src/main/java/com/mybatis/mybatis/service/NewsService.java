package com.mybatis.mybatis.service;

import com.mybatis.mybatis.entity.Total;
import com.mybatis.mybatis.mapper.mapper1.INewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    INewsMapper iNewsMapper;
    //通过id来多表查询
    public Total getNewsById(Integer id){
        return iNewsMapper.select(id);
    }

    //

}
