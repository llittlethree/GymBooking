package com.fzh.com.sevice;

import com.fzh.com.model.TVenueCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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


    /**
     * 说明: 场地类型
     * @author   zhangxiaosan
     * @create   2021/4/20
     * @param    venueCategoryName 类型名称
     * @return
     */
    Page list(Pageable pageable, String venueCategoryName)throws Exception;

    /**
    * 说明: 根据id软删除
    * @author   zhangxiaosan
    * @create   2021/4/20
    * @param
    * @return
    */
    Integer deleteOne(Integer id)throws Exception;


    /**
     * 说明: 添加类型
     * @author   zhangxiaosan
     * @create   2021/4/20
     * @param
     * @return
     */
    TVenueCategory save(TVenueCategory tVenueCategory)throws Exception;

    /**
     * 说明: 根绝id查找类型
     * @author   zhangxiaosan
     * @create   2021/4/20
     * @param
     * @return
     */
    TVenueCategory getByid(String id)throws Exception;
}
