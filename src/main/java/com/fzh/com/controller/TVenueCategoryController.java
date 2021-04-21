package com.fzh.com.controller;

import com.fzh.com.model.TVenueCategory;
import com.fzh.com.sevice.TVenueCategoryService;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-24 23:27
 * @verson 1.0.0
 */
@RestController
@RequestMapping(value = "venueCategoryApi")
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
    @PostMapping(value = "findAll")
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
    /**
     * 软删除一条记录
     * */
    @RequestMapping(value="deleteOne",method = RequestMethod.POST)
    @ResponseBody()
    public String deleteOne(
            @RequestParam(value ="id")  Integer id
    ){
        if(id==null||id <= 0)return ResponseUtil.error("id不能为空");
        String resStr = "";
        try {
            int res = tVenueCategoryService.deleteOne(id);
            resStr = ResponseUtil.success(res);
        } catch (Exception e) {
            resStr = ResponseUtil.error("异常："+e);
            e.printStackTrace();
        }
        return resStr;
    }
    /**
     *
     * 批量软删除
     * */
    @RequestMapping(value="deleteAll",method = RequestMethod.POST)
    @ResponseBody()
    public String deleteAll(
            @RequestParam(value ="ids")  String ids
    ){
        if(StringUtil.isEmpty(ids))return ResponseUtil.error("id不能为空");
        String resStr = "";
        try {
            System.out.println(ids);
            String[] ids_str = ids.split(",");
            System.out.println(ids_str);
            int res =0;
            for (String id:ids_str) {
                res = tVenueCategoryService.deleteOne(Integer.valueOf(id));
            }
            resStr = ResponseUtil.success(res);
        } catch (Exception e) {
            resStr = ResponseUtil.error("异常："+e);
            e.printStackTrace();
        }
        return resStr;
    }


    /**
     * 说明: 添加类型
     * @author   zhangxiaosan
     * @create   2021/4/20
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add")
    public String add(@RequestParam(value = "id",required = false) String id,@RequestParam(value = "venueNCategoryName")String venueNCategoryName){
        if(StringUtil.isEmpty(venueNCategoryName)) return ResponseUtil.error("类型名称不能为空");
        String resStr = ResponseUtil.error("添加失败");
        System.out.println("param venueNCategoryName:"+venueNCategoryName);
        try{
            TVenueCategory tVenueCategory = null;
            if (StringUtil.isNoEmpty(id)){
                tVenueCategory= tVenueCategoryService.save(new TVenueCategory().setVenueCategoryName(venueNCategoryName).setId(Long.valueOf(id)));
            }else{
                tVenueCategory= tVenueCategoryService.save(new TVenueCategory().setVenueCategoryName(venueNCategoryName));
            }
            resStr = ResponseUtil.success(tVenueCategory);
        }catch (Exception e){
            resStr = ResponseUtil.error("error e:"+e);
            e.printStackTrace();
        }
        return resStr;
    }

    /**
    * 说明: 根绝id查找类型
    * @author   zhangxiaosan
    * @create   2021/4/20
    * @param
    * @return
    */
    @ResponseBody
    @RequestMapping(value="getByid")
    public String getByid(@RequestParam(value = "id")String id){
        if(StringUtil.isEmpty(id))return ResponseUtil.error("id不能为空");
        String resStr = "";
        try{
            TVenueCategory t = tVenueCategoryService.getByid(id);
            resStr = ResponseUtil.success(t);
        }catch (Exception e){
            resStr = ResponseUtil.error("getByid error："+e);
            e.printStackTrace();
        }
        return resStr;
    }
}
