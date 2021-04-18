package com.fzh.com.controller;

import com.fzh.com.model.TVenueCategory;
import com.fzh.com.sevice.TBookingService;
import com.fzh.com.sevice.TVenueCategoryService;
import com.fzh.com.sevice.TVenueService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.PageUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 张小三
 * @create 2021-04-18 15:53
 * @verson 1.0.0
 */

@RequestMapping(value = "venue")
@Controller
public class VenueController {
    @Autowired
    private TVenueService tVenueService;
    @Autowired
    private TVenueCategoryService tVenueCategoryService;

    /**
    * 说明: 显示场地列表页面
    * @author   zhangxiaosan
    * @create   2021/4/18
    * @param
    * @return
    */
    @RequestMapping("showList")
    public String showList(Model model){
        List<TVenueCategory> venueCategoryList = null;
        try {
            venueCategoryList = tVenueCategoryService.findAll("");
            model.addAttribute("venueCategoryList",venueCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "venue";
    }


    /**
    * 说明: 场地列表
    * @author   zhangxiaosan
    * @create   2021/4/18
     * @param page Integer 当前页码
     * @param pageSize Integer 每页显示的数量
     * @param order String 排序规则
     * @param orderBy String 排序条件
     * @param venueCategoryId String 场地类型
     * @param venueName String 场地名称
     * @param price String 场地价格
     * @param maxUse String 场地最大使用量
     * @param createTimeStart String 创建开始时间
     * @param createTimeEnd String 创建结束时间
    * @return
    */
    @RequestMapping(value = "list")
    @ResponseBody
    public String list(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order",defaultValue = "desc") String order,
            @RequestParam(value = "orderBy",defaultValue = "id") String orderBy,
            @RequestParam(value = "venueCategoryId",required = false) String venueCategoryId,
            @RequestParam(value = "venueName",required = false) String venueName,
            @RequestParam(value = "price",required = false) String price,
            @RequestParam(value = "maxUse",required = false) String maxUse,
            @RequestParam(value = "createTimeStart",required = false) String createTimeStart,
            @RequestParam(value = "createTimeEnd",required = false) String createTimeEnd
    ){
        String resStr = ResponseUtil.layuiTablePage(0, "查询失败", null,0);
        String start = null,end = null;
        if(StringUtil.isNoEmpty(createTimeStart)) start = DateUtil.dateToTimeStamp(createTimeStart).toString();
        if(StringUtil.isNoEmpty(createTimeEnd)) end = DateUtil.dateToTimeStamp(createTimeEnd).toString();
        Pageable pageable = PageUtil.page(page, pageSize, order, orderBy);
        try {
            Page list = tVenueService.list(pageable, venueCategoryId, venueName, start,end,price,maxUse);
            System.out.println(list);
            Map map = PageUtil.pageFormart(list);
            resStr = ResponseUtil.layuiTablePage(0, "成功", map.get("datas"),Integer.valueOf(map.get("total").toString()) );
        }catch (Exception e){
            resStr = ResponseUtil.layuiTablePage(0, "查询异常", e,0);
            e.printStackTrace();
        }
        return resStr;
    }
    /**
     * 软删除一条记录
     * */
    @RequestMapping(value="/deleteOne",method = RequestMethod.POST)
    @ResponseBody()
    public String deleteOne(
            @RequestParam(value ="id")  Integer id
    ){
        if(id==null||id <= 0)return ResponseUtil.error("id不能为空");
        String resStr = "";
        try {
            int res = tVenueService.deleteOne(id);
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
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
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
                res = tVenueService.deleteOne(Integer.valueOf(id));
            }
            resStr = ResponseUtil.success(res);
        } catch (Exception e) {
            resStr = ResponseUtil.error("异常："+e);
            e.printStackTrace();
        }
        return resStr;
    }
}
