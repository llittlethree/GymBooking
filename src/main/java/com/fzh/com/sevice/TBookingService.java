package com.fzh.com.sevice;

import com.fzh.com.model.TBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-24 22:53
 * @verson 1.0.0
 */
public interface TBookingService {
    /**
     * 说明: 预约提交
     * @author   zhangxiaosan
     * @create   2021/4/11
     * @param tBooking TBooking
     * @return bookingPhone 预留手机号
     */
    TBooking save(TBooking tBooking) throws Exception;

    /**
    * 说明: 判断预约的时间段内是否已经存在预约
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param    bookingStartTime String 开始时间
    * @param    bookingEndTime String 结束时间
    * @return
    */
    Boolean canBookingByTime(String bookingStartTime,String bookingEndTime) throws Exception;

    /**
    * 说明: 判断当前用户是否已经存在预约
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param    userid 用户id
    * @return   true 已经存在预约
    */
    Boolean isBookingAlready(Long userid) throws Exception;

    /**
     * 说明: 分页查找预约记录
     * @author   zhangxiaosan
     * @create   2021/4/11
     * @param pageable 分页
     * @param number String 预约号
     * @param bookingUserid String 预约者id
     * @param bookingStartTime String 预约开始时间
     * @param bookingEndTime String 预约结束时间
     * @param bookingStatus String 预约状态 默认1 预约成功
     * @return
     */
    Page list(Pageable pageable, String number, String bookingUserid, String bookingStartTime, String bookingEndTime, String bookingStatus)throws Exception;

    /**
     * 说明:五分钟执行一次，更新预约状态，判断是否逾期
     * @author   zhangxiaosan
     * @create   2021/4/11
     * @param
     * @return
     */
    List<TBooking> findByBookingStatus(Integer bookingStatus)throws Exception;
}
