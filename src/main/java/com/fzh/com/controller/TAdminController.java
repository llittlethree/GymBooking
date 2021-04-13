package com.fzh.com.controller;

import com.fzh.com.model.TVenue;
import com.fzh.com.sevice.TAdminService;
import com.fzh.com.sevice.TVenueService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.PageUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 张小三
 * @create 2021-03-24 22:49
 * @verson 1.0.0
 */
@RestController
@RequestMapping(value = "adminApi")
public class TAdminController {
    @Autowired
    private TAdminService tAdminService;

    /**
     * 说明: 分页查找管理员
     * @author   zhangxiaosan
     * @create   2021/3/25
     * @param page Integer 当前页码
     * @param pageSize Integer 每页显示的数量
     * @param order String 排序规则
     * @param orderBy String 排序条件
     * @param adminPhone 手机号
     * @param adminName 名称
     * @param adminSex 性别
     * @param createStartTime 开始时间
     * @param createEndTime 结束时间
     * @param adminStatus 状态
     * @return
     */
    @PostMapping(value = "/list")
    public String list(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order",defaultValue = "desc") String order,
            @RequestParam(value = "orderBy",defaultValue = "id") String orderBy,

            @RequestParam(value = "adminPhone",required = false) String adminPhone,
            @RequestParam(value = "adminName",required = false) String adminName,
            @RequestParam(value = "adminSex",required = false) String adminSex,
            @RequestParam(value = "createStartTime",required = false) String createStartTime,
            @RequestParam(value = "createEndTime",required = false) String createEndTime,
            @RequestParam(value = "adminStatus",required = false) String adminStatus
    ){
        System.out.println("venueApi list start!");
        System.out.println("param page:"+page);
        System.out.println("param pageSize:"+pageSize);
        System.out.println("param order:"+order);
        System.out.println("param orderBy:"+orderBy);
        System.out.println("param adminPhone:"+adminPhone);
        System.out.println("param adminName:"+adminName);
        System.out.println("param adminSex:"+adminSex);
        System.out.println("param createStartTime:"+createStartTime);
        System.out.println("param createEndTime:"+createEndTime);
        System.out.println("param adminStatus:"+adminStatus);

        String resStr="";
        try {
            Pageable pageable = PageUtil.page(page, pageSize, order, orderBy);
            Page list = tAdminService.list(pageable, adminPhone, adminName, adminSex, createStartTime,createEndTime,adminStatus);
            System.out.println(list);
            Map map = PageUtil.pageFormart(list);
            resStr = ResponseUtil.success(map);
        }catch (Exception e){
            resStr = ResponseUtil.error("查询异常，"+e);
            e.printStackTrace();
        }
        System.out.println("venueApi list End!");
        return  resStr;
    }


}
