package com.zyc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录页面
 * @author zyc
 * @version 1.0
 */
@Controller
public class RedirectController {

    @GetMapping("/{url}")
    public String url(@PathVariable("url") String url){
        return url;
    }

    /**
     * 配置这个是应为thymeleaf模板需要但是没有这个资源，所以才配置的
     */
    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon(){

    }
}
