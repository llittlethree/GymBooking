package com.fzh.com.controller;

import com.fzh.com.model.TVenueCategory;
import com.fzh.com.sevice.TVenueCategoryService;
import com.fzh.com.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-24 23:27
 * @verson 1.0.0
 */
@RestController
@RequestMapping(name = "/venueCategoryApi")
public class TVenueCategoryController {
    @Autowired
    private TVenueCategoryService tVenueCategoryService;

    /**
     * 说明: 获取所有场地类型列表 （不分页）
     * @author   zhangxiaosan
     * @create   2021/3/24
     * @param    venueCategoryName String 场地名称 可空
     * @return   场地列表 List<TVenueCategory>
     */
    @PostMapping(value = "/findAll")
    public String findAll(@RequestParam(value = "venueCategoryName",required = false) String venueCategoryName) {
        System.out.println(" findAll Start!");
        System.out.println(" venueCategoryName Param:"+venueCategoryName);
        String resStr="";
        try{
            List<TVenueCategory> all = tVenueCategoryService.findAll(venueCategoryName);
            resStr = ResponseUtil.success(all);
        }catch (Exception e){
            resStr = ResponseUtil.error("findAll Error:"+e);
            e.printStackTrace();
        }
        System.out.println(" findAll End!");
        return resStr;
    }

}
