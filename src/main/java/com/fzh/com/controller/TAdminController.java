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

    /**
     * 说明: 根据id查找详情
     * @author   zhangxiaosan
     * @create   2021/4/1
     * @param  id Integer 场地id
     * @return
     */
    @PostMapping(value ="/getByid")
    public String getByid(@RequestParam(value = "id")Integer id){
        System.out.println("getByid Start!");
        System.out.println("param id:"+id);
        String resStr ="";
        if (id == null || id <= 0 ) return ResponseUtil.error("id 不能为空");
        try{
            TVenue tVenue = tVenueService.findById(id);
            resStr = ResponseUtil.success(tVenue);
        }catch (Exception e){
            resStr = ResponseUtil.error("Error:"+e);
            resStr = ResponseUtil.error("getByid Error:"+e);
        }
        System.out.println("getByid End!");
        return resStr;
    }

    /**
     * 添加场地
     */
    @PostMapping(name = "/add")
    public String add(
            @RequestParam(value = "venueCategoryId",defaultValue = "1") String venueCategoryId,
            @RequestParam(value = "venueName" ) String venueName,
            @RequestParam(value = "price",defaultValue = "0.0") String price,
            @RequestParam(value = "remark" ,required = false) String remark,
            @RequestParam(value = "maxUse",defaultValue = "1" ) String maxUse
    ){
        System.out.println("add Start");
        String resStr = "";
        if(StringUtil.isEmpty(venueCategoryId)) return ResponseUtil.error("类型id不能为空");
        if(StringUtil.isEmpty(venueName)) return ResponseUtil.error("场地名称不能为空");
        if(StringUtil.isEmpty(price)) return ResponseUtil.error("价格不能为空");
        if(StringUtil.isEmpty(maxUse)) return ResponseUtil.error("最大使用量不能为空");
        if(Integer.valueOf(maxUse) <= 0) return ResponseUtil.error("最大使用量必须大于等于1");
        if(BigDecimal.valueOf(Double.valueOf(price)).compareTo(BigDecimal.ZERO) == -1) return ResponseUtil.error("价格不能小于0");
        System.out.println("add End");
        try{
            TVenue tVenue =  tVenueService.save(
                    new TVenue()
                            .setVenueCategoryId(Long.valueOf(venueCategoryId))
                            .setVenueName(venueName)
                            .setPrice(BigDecimal.valueOf(Double.valueOf(price)))
                            .setCreateTime(DateUtil.getTimeStampNow())
                            .setRemark(remark)
                            .setMaxUse(Integer.valueOf(maxUse))
            );
            resStr = ResponseUtil.success(tVenue);
        }catch (Exception e){
            resStr = ResponseUtil.error("add Error:"+e);
            e.printStackTrace();
        }
        return resStr ;
    }


    /**
     * 修改场地
     * */
    @PostMapping(value = "/update")
    public String update(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "venueCategoryId",defaultValue = "1") String venueCategoryId,
            @RequestParam(value = "venueName" ) String venueName,
            @RequestParam(value = "price",defaultValue = "0.0") String price,
            @RequestParam(value = "remark" ,required = false) String remark,
            @RequestParam(value = "maxUse",defaultValue = "1" ) String maxUse
    ){
        System.out.println("update Start!");
        String resStr = "";
        if (StringUtil.isEmpty(id)) return ResponseUtil.error("id不能为空");
        if (StringUtil.isEmpty(venueCategoryId)) return  ResponseUtil.error("场地类型id不能为空");
        if (StringUtil.isEmpty(venueName)) return ResponseUtil.error("场地名称不能为空");
        if (StringUtil.isEmpty(price)) return ResponseUtil.error("价格不能为空");
        if (StringUtil.isEmpty(maxUse)) return ResponseUtil.error("最大使用量不能为空");

        //根据id查找场地
        try {
            TVenue byId = tVenueService.findById(Long.valueOf(id));
            if (byId == null) return ResponseUtil.error("查找不到对应的数据");

            TVenue tVenue =  tVenueService.save(
                    new TVenue()
                            .setId(Long.valueOf(id))
                            .setVenueCategoryId(Long.valueOf(venueCategoryId))
                            .setVenueName(venueName)
                            .setPrice(BigDecimal.valueOf(Double.valueOf(price)))
                            .setCreateTime(DateUtil.getTimeStampNow())
                            .setRemark(remark)
                            .setMaxUse(Integer.valueOf(maxUse))
            );
            resStr = ResponseUtil.success(tVenue);

        } catch (Exception e) {
            resStr = ResponseUtil.error("update Error:" + e);
            e.printStackTrace();
        }

        System.out.println("update End!");
        return resStr;
    }
}
