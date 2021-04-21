package com.fzh.com.controller;

import com.fzh.com.sevice.TBookingService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.PageUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 张小三
 * @create 2021-04-16 0:18
 * @verson 1.0.0
 */
@RequestMapping(value = "booking")
@Controller
public class BookingController {

    @Autowired
    private TBookingService tBookingService;


    /**
     * 说明: 后台 显示预约页面
     * @author   zhangxiaosan
     * @create   2021/4/16
     * @param    bookingStatus 预约状态 默认1   0取消预约 1预约成功，2预约失败 3预约已核销 4预约逾期
     * @return
     */
    @RequestMapping(value = "showBooking",method = RequestMethod.GET)
    public String showBooking(Model model, HttpSession session, @RequestParam(value = "bookingStatus",defaultValue ="1" )Integer  bookingStatus){
        if (session.getAttribute("adminLoginInfo")==null) return "index";
        model.addAttribute("bookingStatus",bookingStatus);
        return "showBooking";
    }


    /**
     * 说明: 分页查找预约记录
     * @author   zhangxiaosan
     * @create   2021/4/11
     * @param page Integer 当前页码
     * @param pageSize Integer 每页显示的数量
     * @param order String 排序规则
     * @param orderBy String 排序条件
     * @param number String 预约号
     * @param bookingUserid String 预约者id
     * @param bookingStartTime String 预约开始时间
     * @param bookingEndTime String 预约结束时间
     * @param bookingStatus String 预约状态 默认1 预约成功
     * @param bookingPhone String 预约手机号
     * @param number String 预约号
     * @return
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public String list(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order",defaultValue = "desc") String order,
            @RequestParam(value = "orderBy",defaultValue = "id") String orderBy,
            @RequestParam(value = "number",defaultValue = "") String number,
            @RequestParam(value = "bookingUserid",defaultValue = "") String bookingUserid,
            @RequestParam(value = "bookingStartTime",defaultValue = "") String bookingStartTime,
            @RequestParam(value = "bookingEndTime",defaultValue = "") String bookingEndTime,
            @RequestParam(value = "bookingStatus",defaultValue = "1") String bookingStatus,
            @RequestParam(value = "bookingPhone",defaultValue = "") String bookingPhone
    ){
        System.out.println("venueApi list start!");
        System.out.println("param page:"+page);
        System.out.println("param pageSize:"+pageSize);
        System.out.println("param order:"+order);
        System.out.println("param orderBy:"+orderBy);
        System.out.println("param number:"+number);
        System.out.println("param bookingUserid:"+bookingUserid);
        System.out.println("param bookingStartTime:"+bookingStartTime);
        System.out.println("param bookingEndTime:"+bookingEndTime);
        System.out.println("param bookingStatus:"+bookingStatus);


        String resStr="";
        try {
            String start = null,end = null;
            if(StringUtil.isNoEmpty(bookingStartTime)) start = DateUtil.dateToTimeStamp(bookingStartTime).toString();
            if(StringUtil.isNoEmpty(bookingEndTime)) end = DateUtil.dateToTimeStamp(bookingEndTime).toString();
            Pageable pageable = PageUtil.page(page, pageSize, order, orderBy);
            Page list = tBookingService.list(pageable, number, bookingUserid, start,end,bookingStatus,bookingPhone);
            System.out.println(list);
            Map map = PageUtil.pageFormart(list);
            resStr = ResponseUtil.layuiTablePage(0, "成功", map.get("datas"),Integer.valueOf(map.get("total").toString()) );
        }catch (Exception e){
            resStr = ResponseUtil.layuiTablePage(0, "查询异常", e,0);
            e.printStackTrace();
        }
        System.out.println("tBooking list End!");
        return  resStr;
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
            int res = tBookingService.deleteOne(id);
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
                res = tBookingService.deleteOne(Integer.valueOf(id));
            }
            resStr = ResponseUtil.success(res);
        } catch (Exception e) {
            resStr = ResponseUtil.error("异常："+e);
            e.printStackTrace();
        }
        return resStr;
    }
}
