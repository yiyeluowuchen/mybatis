package com.mybatis.mybatis.controller;

import com.mybatis.mybatis.entity.Auth;
import com.mybatis.mybatis.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthService authService;


    @GetMapping("/logins")
    public  String login(HttpServletRequest request){

    String username = request.getParameter("username");
    String password = request.getParameter("password");

        Auth auth= authService.getAuth(username,password);
        if(auth == null){
            logger.error("登陆失败");
            return "登陆失败";
        }else{
            logger.info("登陆成功");
            return "登陆成功";
        }
    }


    @GetMapping("/p/s")
    public String authAdmin(){
        return "admin-权限";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/a/s")
    public String authUser(){
        return "user-权限";
    }
}
