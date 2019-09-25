package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api(tags = "页面注册")
public class RegisterController {

    @Autowired
    UserService userService;

    @ApiOperation("页面注册")
    @RequestMapping(value = "/register",method =  RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("register",new User());

        return "register1";

    }


    @ApiOperation("页面注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String   toRegister (@ModelAttribute User user){
        System.out.println(user+"________________");
        User u = userService.selectByName(user.getUserName());
        System.out.println(u+"----------------");
        if(u != null ){
            return   "false";
        }
        else{
             userService.insertUser(user);
             return "login1";
        }

    }






}
