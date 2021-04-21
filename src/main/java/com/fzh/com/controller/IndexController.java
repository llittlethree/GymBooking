package com.fzh.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 后台登录
 * @author 张小三
 * @create 2021-04-15 22:31
 * @verson 1.0.0
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "/")
    public String login(){
        return "index";
    }


}
