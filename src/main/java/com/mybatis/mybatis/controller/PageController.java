package com.mybatis.mybatis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = "page")
    public String gg() {
        return "webSocket";
    }

}