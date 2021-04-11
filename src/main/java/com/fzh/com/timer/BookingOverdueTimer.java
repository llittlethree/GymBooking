package com.fzh.com.timer;

import com.fzh.com.model.TBooking;
import com.fzh.com.sevice.TBookingService;
import com.fzh.com.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**定时任务，五分钟执行一次，检测预约是否预期
 * @author 张小三
 * @create 2021-04-11 16:33
 * @verson 1.0.0
 */
@Component
public class BookingOverdueTimer{
    @Autowired
    private TBookingService tBookingService;
    /**
    * 说明:五分钟执行一次
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param
    * @return
    */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void checkBookingOverdue(){
        //查找预约成功的记录
        Integer bookingStatus = 1; //预约成功 状态 0取消预约 1预约成功，2预约失败 3预约已核销 4预约逾期',
        try {
            List<TBooking> tBookingList = tBookingService.findByBookingStatus(bookingStatus);
            for (TBooking tBooking:tBookingList) {
                long bookingEndTime = tBooking.getBookingEndTime();//结束时间
                //获取现在的时间戳
                Integer timeStampNow = DateUtil.getTimeStampNow();
                if(timeStampNow >= bookingEndTime){
                    //当前时间 > 预约结束时间 : 已逾期
                    tBooking.setBookingStatus(4);
                    tBookingService.save(tBooking);//修改预约状态为逾期
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
