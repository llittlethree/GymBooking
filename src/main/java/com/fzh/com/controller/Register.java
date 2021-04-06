package com.fzh.com.controller;

import com.fzh.com.sevice.AdminService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import com.fzh.com.utils.privacy.Aes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-14 16:20
 * @verson 1.0.0
 */
@RestController
@RequestMapping(value = "/register")
public class Register extends BaseController{
    @Autowired
    private AdminService adminService;

    /**
    * 说明: 管理员注册
    * @author   zhangxiaosan
    * @create   2021/3/14
    * @param adminPhone  String 管理员手机号
    * @param adminSex  String 管理员性别 1男(默认) 0女
    * @param adminName  String 管理员姓名
    * @param adminPassword  String 管理员密码
    * @param adminStatus  String 管理员状态 0正常(默认) 1限制 2.待审核
    * @return
    */
    @RequestMapping(value = "/doRegister")
    public String doRegister(
            @RequestParam(value = "adminPhone") String adminPhone,
            @RequestParam(value = "adminSex",defaultValue = "1") String adminSex,
            @RequestParam(value = "adminName") String adminName,
            @RequestParam(value = "adminPassword") String adminPassword,
            @RequestParam(value = "adminStatus",defaultValue = "2") String adminStatus
    ){
        if (StringUtil.isEmpty(adminPhone)) return ResponseUtil.error("手机号不能为空");
        if (StringUtil.isEmpty(adminSex)) return ResponseUtil.error("性别不能为空");
        if (StringUtil.isEmpty(adminPassword)) return ResponseUtil.error("密码不能为空");
        if (StringUtil.isEmpty(adminStatus)) return ResponseUtil.error("状态不能为空");
        String resStr = "";

        return resStr;
    }
}
