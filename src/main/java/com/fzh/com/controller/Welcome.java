package com.fzh.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张小三
 * @create 2021-03-14 20:03
 * @verson 1.0.0
 */
@Controller
@RequestMapping(value = "/welcome")
public class Welcome extends BaseController{
    /**
    * 说明: 显示登录成功后的后台页面
    * @author   zhangxiaosan
    * @create   2021/3/14
    * @param
    * @return
    */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model){
        return "welcome";
    }
}
