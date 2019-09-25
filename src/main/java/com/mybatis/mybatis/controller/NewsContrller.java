package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.News;
import com.mybatis.mybatis.entity.Total;
import com.mybatis.mybatis.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "api使用" ,tags = "api使用")
@RestController
public class NewsContrller {

    @Autowired
    NewsService newsService;
    //通过id来多表查询
    @ApiOperation("通过id来多表查询")
    @RequestMapping(value = "/getNewsById/{id}",method = RequestMethod.GET)
    public Total getNewsById(@PathVariable  Integer id){
        return newsService.getNewsById(id);
    }

}
