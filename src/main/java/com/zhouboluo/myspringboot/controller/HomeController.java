package com.zhouboluo.myspringboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//返回页面使用@Controller，如果要返回JSON数据，使用@RestController
@Controller
public class HomeController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    //路由使用@RequestMapping设定，也可以给controller加@RequestMapping
    @RequestMapping(value = "/")
    public String home() {
        //直接返回一个页面，果不是模板，在页面名前加forward:，后面跟上文件名，搜索顺序是templates->static->public
        //return "forward:index.html";
        return "hello";
    }

    @RequestMapping(value = ERROR_PATH)
    public String handleError() {
        return "forward:index.html";//404错误返回主页，由前端自行处理路由
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
