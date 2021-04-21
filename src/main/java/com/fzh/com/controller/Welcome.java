package com.fzh.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 张小三
 * @create 2021-03-14 20:03
 * @verson 1.0.0
 */
@Controller
@RequestMapping(value = "/welcome")
public class Welcome {

    /**
    * 说明: 显示登录成功后的后台页面
    * @author   zhangxiaosan
    * @create   2021/3/14
    * @param
    * @return
    */
    @RequestMapping(value = "/welcome")
    public String welcome(Model model,HttpSession session){
        if (session.getAttribute("adminLoginInfo")==null) return "index";
        return "welcome";
    }

    /**
    * 说明: welcome 默认页面
    * @author   zhangxiaosan
    * @create   2021/4/20
    * @param
    * @return
    */
    @RequestMapping(value = "/default")
    public String defaultPage(Model model,HttpSession session){
        if (session.getAttribute("adminLoginInfo")==null) return "index";
        return "default";
    }
}
