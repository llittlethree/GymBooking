package com.fzh.com.controller;

import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.privacy.Aes;
import com.fzh.com.utils.privacy.Md5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台登录
 * @author 张小三
 * @create 2021-03-14 3:33
 * @verson 1.0.0
 */
@Controller
@RequestMapping(value = "/login")
public class Login extends BaseController{




    /**
    * 说明: 显示登录页面
    * @author   zhangxiaosan
    * @create   2021/3/14
    * @param
    * @return
    */
    @RequestMapping(value = "/login")
    public String login(Model model){
        return "index";
    }

    //@
    @ResponseBody
    @PostMapping(value = "/doLogin")
    public String doLogin(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "password") String password,
            HttpSession session

    ){

        return null;
    }


    @GetMapping("/info")
    @ResponseBody
    public String getInfo(
            @RequestParam(value = "token") String token
    ){
        return null;
    }

    @PostMapping(value = "/logout")
    @ResponseBody
    public String logout(@RequestHeader(value = "X-Token") String token){
        return null;
    }
}
