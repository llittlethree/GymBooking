package com.fzh.com.controller;

import com.fzh.com.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-14 22:20
 * @verson 1.0.0
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    /**
    * 说明: 查询管理员列表
    * @author   zhangxiaosan
    * @create   2021/3/14
    * @param adminName String 姓名
    * @param adminSex Integer 性别 1男 0女
    * @param  adminStatus Integer 状态 0正常(默认) 1限制
    * @return
    */
    @RequestMapping(value = "/pageList")
    @ResponseBody
    public String pageList(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "limit",defaultValue = "10") Integer limit,

            @RequestParam(value = "adminName",required = false) String adminName,
            @RequestParam(value = "adminSex",required = false) Integer adminSex,
            @RequestParam(value = "adminStatus",required = false) Integer adminStatus
    ){
        String resStr = "";

        return resStr;
    }
}
