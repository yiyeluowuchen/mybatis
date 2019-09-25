package com.mybatis.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GradeController {

    /**
     * 访问欢迎页面
     * @return
     */
    @GetMapping("/")
    public String  index(){
        return "welcome";
    }

    /**
     * 链接到grade1界面
     * @param path
     * @return
     */
    @GetMapping("/grade1/{path}")
    public String grade1(@PathVariable String  path){
            return "grade1/"+path;
    }

    /**
     * 链接到grade2界面
     */
    @GetMapping("/grade2/{path}")
    public String grade2(@PathVariable String path){
            return "grade2/" +path;
    }

    /**|
     * 链接到grade3界面
     * @param path
     * @return
     */
    @GetMapping("/grade3/{path}")
    public String grade3(@PathVariable String path){
            return "grade3/"+path;
    }


    /**
     * 登陆界面
     */
    @GetMapping("/userlogin")
    public String loginPage(){
        return "login";
    }







}



