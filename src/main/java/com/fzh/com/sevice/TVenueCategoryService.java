package com.fzh.com.sevice;

import com.fzh.com.model.TVenueCategory;

import java.util.List;

public interface TVenueCategoryService {
    /**
    * 说明: 获取所有场地类型列表 （不分页）
    * @author   zhangxiaosan
    * @create   2021/3/24
    * @param    venueCategoryName String 场地名称
    * @return   场地列表 List<TVenueCategory>
    */
    List<TVenueCategory> findAll(String venueCategoryName) throws Exception;
}
