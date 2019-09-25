package com.mybatis.mybatis.service;


import com.mybatis.mybatis.entity.Socks;

import com.mybatis.mybatis.mapper.mapper1.ISocksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksService {

    @Autowired
    ISocksMapper iSocksMapper;


             public int insert(Socks socks){
                 return iSocksMapper.insert(socks);
             }


            public List<Socks> select(Socks socks){
                 return iSocksMapper.select(socks);
                 }





}



