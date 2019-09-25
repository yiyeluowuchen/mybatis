package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.Socks;
import com.mybatis.mybatis.service.SocksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "使用定时简单例子")
@RestController
public class ISocksController {

    @Autowired
    SocksService socksService;
    @ApiOperation("插入socks")
    @RequestMapping(value = "/insert" ,method = RequestMethod.GET)
    public String insert(Socks socks){

        int result = socksService.insert(socks);
        if(result > 0){
            return "插入成功！";
        }
        else{
            return "插入失败!";
        }

    }


    @ApiOperation("通过id来找")
    @RequestMapping(value = "/select" ,method = RequestMethod.GET)
    public List<Socks> select (Socks socks){

        return socksService.select(socks);
    }








}
