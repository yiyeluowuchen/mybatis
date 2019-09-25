package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.db1.Person;
import com.mybatis.mybatis.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api( value = "使用多数据源druid-db1" ,tags = "使用多数据源druid-db1")
public class PersonController {

    @Autowired
    PersonService personService;

    @ApiOperation(value = "插入db1-person中一个数据")
    @GetMapping("/insertPerson")
    public String insertPerson(Person person){

        personService.insertPerson(person);
        return "成功";
    }
}
