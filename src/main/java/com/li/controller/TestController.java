package com.li.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/testController")
public class TestController {
    private Logger logger = Logger.getLogger(TestController.class);
    //通过Jpa进行的查询
    @RequestMapping(value = "test")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("跳转页面");
        return "/test";
    }
}


















