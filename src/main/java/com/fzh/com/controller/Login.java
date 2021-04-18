package com.fzh.com.controller;

import com.fzh.com.model.TAdmin;
import com.fzh.com.sevice.TAdminService;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import com.fzh.com.utils.privacy.Aes;
import com.fzh.com.utils.privacy.Md5;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TAdminService tAdminService;


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
        System.out.println("param phone:"+phone);
        System.out.println("param password:"+password);
        if(StringUtil.isEmpty(phone)) return ResponseUtil.error("请输入手机号");
        if(StringUtil.isEmpty(password)) return ResponseUtil.error("请输入密码");
        String resStr = ResponseUtil.error("登录失败，请检查账号密码是否正确");
        try {
            TAdmin tAdmin = tAdminService.findByAdminPhoneAndAdminPasswordAndAdminStatus(phone, password, 0);
            if(tAdmin == null)  return ResponseUtil.error("登录失败，请检查账号密码是否正确");
            resStr = ResponseUtil.success("登录成功");
            session.setAttribute("adminLoginInfo",tAdmin);
        }catch (Exception e){
            resStr = ResponseUtil.error("login Error:"+e);
            e.printStackTrace();
        }
        return  resStr ;
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
