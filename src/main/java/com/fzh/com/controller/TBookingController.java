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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.Map;
import java.util.StringTokenizer;

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
            @RequestParam(value = "widthNum",defaultValue = "1") String widthNum,
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
                    .setBookingUserid(Long.valueOf(bookingUserid))
                    .setVenueId(Long.valueOf(venueId))
                    .setBookingStartTime(Long.valueOf(DateUtil.dateToTimeStamp(bookingStartTime)))
                    .setBookingEndTime(Long.valueOf(DateUtil.dateToTimeStamp(bookingEndTime)))
                    .setCreateTime(Long.valueOf(DateUtil.getTimeStampNow()))
                    .setBookingStatus(1)
                    .setRemark(remark)
                    .setBookingPhone(bookingPhone)
                    .setWidthNum(Integer.valueOf(widthNum))
                    .setBookingStudentNum(bookingStudentNum)
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
     * @param bookingPhone String 预约手机号
     * @param number String 预约号
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
            resStr = ResponseUtil.success(map);
        }catch (Exception e){
            resStr = ResponseUtil.error("查询异常，"+e);
            e.printStackTrace();
        }
        System.out.println("tBooking list End!");
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
        System.out.println("tBooking thisTimeBooked Start!");
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
            resStr = ResponseUtil.error("tBooking thisTimeBooked Error:"+e);
            e.printStackTrace();
        }
        System.out.println("tBooking thisTimeBooked end!");
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
        System.out.println("tBooking ishaveByUserId Start!");
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
        System.out.println("tBooking ishaveByUserId end!");
        return  resStr;
    }

    /***
     * 根据id获取预约记录
     * @param id
     */
    @PostMapping(value = "getById")
    public String getById(@RequestParam("id") String id){
        System.out.println("tbookingApi getById Start");
        if(StringUtil.isEmpty(id))return ResponseUtil.error("id不能为空");
        String resStr ="";
        try{
            TBooking tBooking = tBookingService.getById(id);
            resStr = ResponseUtil.success(tBooking);
        }catch (Exception e){
            resStr = ResponseUtil.error("ishaveByUserid error:"+e);
            e.printStackTrace();
        }
        System.out.println("tbookingApi getById End");
        return resStr;
    }

    /**
    * 说明:根据id 修改预约状态
    * @author   zhangxiaosan
    * @create   2021/4/14
    * @param    id
    * @param    bookingStatus 预约状态 0取消预约 1预约成功，2预约失败 3预约已核销 4预约逾期
    * @return
    */
    @PostMapping(value = "changeBookingStatusById")
    public String changeBookingStatusById(
        @RequestParam(value = "id") String id ,
        @RequestParam(value = "bookingStatus") String bookingStatus
    ){
        System.out.println("changeBookingStatusById Start");
        if(StringUtil.isEmpty(id))return ResponseUtil.error("id不能为空");
        if(StringUtil.isEmpty(bookingStatus))return ResponseUtil.error("预约状态不能为空");
        String resStr="";
        try{
            TBooking tBooking = tBookingService.getById(id);
            if(tBooking == null) return ResponseUtil.error("找不到预约记录");
            tBooking.setBookingStatus(Integer.valueOf(bookingStatus));
            TBooking save = tBookingService.save(tBooking);
            resStr = ResponseUtil.success(save);
        }catch (Exception e){
            resStr = ResponseUtil.error("changeBookingStatusById error:"+e);
            e.printStackTrace();
        }
        System.out.println("changeBookingStatusById End");
        return resStr;
    }


}
