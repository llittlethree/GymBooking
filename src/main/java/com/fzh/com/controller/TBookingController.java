package com.fzh.com.controller;

import com.fzh.com.model.TBooking;
import com.fzh.com.sevice.TBookingService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.PageUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.Map;

/**
 * @author 张小三
 * @create 2021-03-24 22:50
 * @verson 1.0.0
 */
@RestController
@RequestMapping(value = "/bookingApi")
public class TBookingController {
    @Autowired
    private TBookingService tBookingService;

    /**
    * 说明: 预约提交
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param bookingUserid 预约者id
    * @param venueId 预约场地id
    * @param  bookingStartTime 开始时间
    * @param bookingEndTime 结束时间
    * @param remark 预约备注
    * @return bookingPhone 预留手机号
    */
    @PostMapping(value = "/bookingStart")
    public String bookingStart(
            @RequestParam(value = "bookingUserid") String bookingUserid,
            @RequestParam(value = "venueId") String venueId,
            @RequestParam(value = "bookingStartTime") String bookingStartTime,
            @RequestParam(value = "bookingEndTime") String bookingEndTime,
            @RequestParam(value = "remark") String remark,
            @RequestParam(value = "bookingPhone") String bookingPhone,
            @RequestParam(value = "widthNum") String widthNum,
            @RequestParam(value = "bookingStudentNum") String bookingStudentNum
    ){
        System.out.println("bookingStart Start");
        System.out.println("param  bookingUserid:"+bookingUserid);
        System.out.println("param  venueId:"+venueId);
        System.out.println("param  bookingStartTime:"+bookingStartTime);
        System.out.println("param  bookingEndTime:"+bookingEndTime);
        System.out.println("param  remark:"+remark);
        System.out.println("param  bookingPhone:"+bookingPhone);
        System.out.println("param  bookingStudentNum:"+bookingStudentNum);
        System.out.println("param  widthNum:"+widthNum);
        String resStr="";
        if (StringUtil.isEmpty(bookingUserid)) return ResponseUtil.error("预约者id不能为空");
        if (StringUtil.isEmpty(venueId)) return ResponseUtil.error("场地id不能为空");
        if (StringUtil.isEmpty(bookingStartTime)) return ResponseUtil.error("开始时间不能为空");
        if (StringUtil.isEmpty(bookingEndTime)) return ResponseUtil.error("结束时间不能为空");
        if (StringUtil.isEmpty(bookingPhone)) return ResponseUtil.error("预留手机号不能为空");
        try{
            //判断是否已经存在一个预约记录
            if(tBookingService.isBookingAlready(Long.valueOf(bookingUserid))) return ResponseUtil.error("您已有预约未核销，暂无法预约");
            //判断预约时间是否有效
            if (tBookingService.canBookingByTime(bookingStartTime,bookingEndTime) == false) return ResponseUtil.error("该时间段已经被预约");

            TBooking tBooking = tBookingService.save(new TBooking()
                .setNumber(DateUtil.getTimeType("yyyyMMddHHmmssSSS"))
                .setVenueId(Integer.valueOf(venueId))
                .setBookingEndTime(DateUtil.dateToTimeStamp(bookingEndTime))
                .setBookingStartTime(DateUtil.dateToTimeStamp(bookingStartTime))
                .setBookingStatus(1)
                .setCreateTime(DateUtil.getTimeStampNow())
                .setRemark(remark)
                .setBookingPhone(bookingPhone)
                .setBookingUserid(Integer.valueOf(bookingUserid))
            );
            resStr = ResponseUtil.success(tBooking);
        }catch (Exception e){
            resStr = ResponseUtil.error("bookingStart error:"+e);
            e.printStackTrace();
        }
        System.out.println("bookingStart End");
        return resStr;
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
    * @return
    */
    @PostMapping(value = "/list")
    public String list(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order",defaultValue = "desc") String order,
            @RequestParam(value = "orderBy",defaultValue = "id") String orderBy,
            @RequestParam(value = "number",defaultValue = "") String number,
            @RequestParam(value = "bookingUserid",defaultValue = "") String bookingUserid,
            @RequestParam(value = "bookingStartTime",defaultValue = "") String bookingStartTime,
            @RequestParam(value = "bookingEndTime",defaultValue = "") String bookingEndTime,
            @RequestParam(value = "bookingStatus",defaultValue = "1") String bookingStatus
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
            Pageable pageable = PageUtil.page(page, pageSize, order, orderBy);
            Page list = tBookingService.list(pageable, number, bookingUserid, bookingStartTime, bookingEndTime,bookingStatus);
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
    * 说明: 验证时间段内是否存在预约
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param
    * @return
    */
    @PostMapping(value = "thisTimeBooked")
    public String thisTimeBooked(
        @RequestParam(value = "bookingStartTime",defaultValue = "") String bookingStartTime,
        @RequestParam(value = "bookingEndTime",defaultValue = "") String bookingEndTime
    ){
        System.out.println("venueApi thisTimeBooked Start!");
        System.out.println("param bookingStartTime:"+bookingStartTime);
        System.out.println("param bookingEndTime:"+bookingEndTime);
        if (StringUtil.isEmpty(bookingStartTime)) return ResponseUtil.error("开始时间不能为空");
        if (StringUtil.isEmpty(bookingEndTime)) return ResponseUtil.error("结束时间不能为空");
        String resStr = "";
        try{
            //判断预约时间是否有效
            Boolean aBoolean = tBookingService.canBookingByTime(bookingStartTime, bookingEndTime);
            if (aBoolean == false) return ResponseUtil.error("该时间段已经被预约");
            resStr = ResponseUtil.success("该时间段可预约");

        }catch (Exception e){
            resStr = ResponseUtil.error("venueApi thisTimeBooked Error:"+e);
            e.printStackTrace();
        }
        System.out.println("venueApi thisTimeBooked end!");
        return  resStr;
    }

    /**
    * 说明: 判断当前用户是否已经预约过一次，且未核销
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param
    * @return
    */
    @PostMapping(value = "ishaveByUserId")
    public String ishaveByUserId(@RequestParam(value = "bookingUserid",defaultValue = "") String bookingUserid){
        System.out.println("venueApi ishaveByUserId Start!");
        String resStr = "";
        try{
            //判断是否已经存在一个预约记录
            Boolean bookingAlready = tBookingService.isBookingAlready(Long.valueOf(bookingUserid));
            if(bookingAlready) return ResponseUtil.error("您已有预约未核销，暂无法预约");
            return ResponseUtil.success("当前用户" +
                    "可预约");
        }catch (Exception e){
            resStr = ResponseUtil.error("ishaveByUserid error:"+e);
            e.printStackTrace();
        }
        System.out.println("venueApi ishaveByUserId end!");
        return  resStr;
    }
}
