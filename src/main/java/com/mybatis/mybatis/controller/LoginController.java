package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.User;
import com.mybatis.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static javafx.beans.binding.Bindings.select;

@Controller
@Api(tags = "测试登陆界面")
public class LoginController {
    @Autowired
    UserService userService;

    @ApiOperation("登陆")
    @GetMapping("/Login")
    public  String goLogin ( Model model){
        model.addAttribute("login",new User());
        System.out.println("看有没有进来ddddddddd+-----------");
        return "login1";
    }
    @ApiOperation("登陆")
    @PostMapping("/Login")
    public String LoginSubmit (@ModelAttribute User user){

        System.out.println("看有没有进来+"+user+"-----------");

//        String username = user.getUserName();
//        String pws = user.getPassword();

         User num = userService.selectLogin(user);
         System.out.println(num+"---------------------");
        if ( num !=null ) {
            return  "result";
        }
        else{
            return "login1";
        }
    }


}
