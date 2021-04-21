package com.fzh.com.controller;


import com.fzh.com.sevice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/base")
@Controller
public class BaseController {
    @Autowired
    AdminService adminService;

    /**
     * 说明: 默认启动页
     * @author   zhangxiaosan
     * @create   2021/3/14
     * @param
     * @return
     */
    @RequestMapping(value = "/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping(value = "/base")
    public String base(Model model , HttpSession session){
        //Boolean isLogin = false;
        Object obj = session.getAttribute("loginInfo");

        //判断是否登录，未登录则强制到登录界面。
        if(obj==null){
            return "index";
        }else{
            /**
             * 将Navs页面中ui中li的导航菜单传递到页面。
             * */
            model.addAttribute("welcome","/welcome/welcome"); //欢迎页

            System.out.println("Base构造方法");

            return "base";
        }

    }





}
