package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.service.CopyTableService;
import com.mybatis.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "复制表mybatis_table")
@RestController
public class CopyTableController {

    @Autowired
    CopyTableService copyTableService;
    @Autowired
    UserService userService;

    /**
     * 复制mytabis_table表数据到db1数据库 copy_mybatis中
     * @return
     */
    @ApiOperation("插入所有记录")
    @GetMapping("/insertAll")
    public String  insertAll(){

        List<User> list = userService.selectAll();
         int result = copyTableService.insertAll(list);

         if(result > 0){
             return "成功";
         }else{
             return "失败";
         }
    }


    /**
     * 插入一条
     */
    @ApiOperation("插入一条")
    @GetMapping("/insertUser2/{id}")
    public String insertUser2 (@PathVariable  int id){
       User user =  userService.sel(id);
       int result = copyTableService.insertUser2(user);
       if(result>0){
           return "成功";
       }else{
           return "失败";
       }

    }

}
