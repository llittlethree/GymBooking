package com.fzh.com.sevice.impl;

import com.fzh.com.model.TBooking;
import com.fzh.com.sevice.TBookingService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.fzh.com.dao.TBookingServiceDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-24 22:54
 * @verson 1.0.0
 */
@Service
public class TBookingServiceImpl implements TBookingService {
    @Autowired
    private TBookingServiceDao tBookingServiceDao;

    /**
     * 说明: 预约提交
     *
     * @param tBooking TBooking
     * @return bookingPhone 预留手机号
     * @author zhangxiaosan
     * @create 2021/4/11
     */
    @Override
    public TBooking save(TBooking tBooking) throws Exception {
        return tBookingServiceDao.save(tBooking);
    }

    /**
     * 说明: 判断预约的时间段内是否已经存在预约
     *
     * @param bookingStartTime String 开始时间
     * @param bookingEndTime   String 结束时间
     * @return true 该时间段内可预约
     * @author zhangxiaosan
     * @create 2021/4/11
     */
    @Override
    public Boolean canBookingByTime(String bookingStartTime, String bookingEndTime) throws Exception {
        List<TBooking> all = tBookingServiceDao.findByBookingStatus(1);
        Integer start = 0, end = 0;
        if (StringUtil.isNoEmpty(bookingStartTime)) start= DateUtil.dateToTimeStamp(bookingStartTime);
        if (StringUtil.isNoEmpty(bookingEndTime)) end = DateUtil.dateToTimeStamp(bookingEndTime);

        boolean flag = true;
        if (all.size() > 0){
            for ( TBooking tBooking : all) {
                long bookingStartTime1 = tBooking.getBookingStartTime(); //开始
                long bookingEndTime1 = tBooking.getBookingEndTime();//结束

                //开始时间或结束时间在已经预约的时间段内，则不可预约
                if (start >= bookingStartTime1 &&  start <= bookingEndTime1){
                    flag = false;
                    break;
                }
                if (end >= bookingStartTime1 &&  end <= bookingEndTime1){
                    flag = false;
                    break;
                }
            }
        }
        return  flag;
    }

    /**
     * 说明: 判断当前用户是否已经存在预约
     *
     * @param userid 用户id
     * @return true 已经存在预约
     * @author zhangxiaosan
     * @create 2021/4/11
     */
    @Override
    public Boolean isBookingAlready(Long userid) throws Exception {
        List<TBooking> all = tBookingServiceDao.findByBookingStatusAndBookingUserid(1,userid);
        if (all.size() > 0) return true;
        return false;
    }

    /**
     * 说明: 分页查找预约记录
     *
     * @param pageable         分页
     * @param number           String 预约号
     * @param bookingUserid    String 预约者id
     * @param bookingStartTime String 预约开始时间
     * @param bookingEndTime   String 预约结束时间
     * @param bookingStatus    String 预约状态 默认1 预约成功
     * @param bookingPhone    String 预约手机号
     * @return
     * @author zhangxiaosan
     * @create 2021/4/11
     */
    @Override
    public Page list(Pageable pageable, String number, String bookingUserid, String bookingStartTime, String bookingEndTime, String bookingStatus,String bookingPhone) throws Exception {
        return tBookingServiceDao.findAll(new Specification<TBooking>() {
            @Override
            public Predicate toPredicate(Root<TBooking> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if(StringUtil.isNoEmpty(number)){
                    predicateList.add(cb.like(root.get("number"), "%"+number+"%"));
                }
                if(StringUtil.isNoEmpty(bookingUserid)){
                    predicateList.add(cb.equal(root.get("bookingUserid"), bookingUserid));
                }
                if(StringUtil.isNoEmpty(bookingStatus)){
                    predicateList.add(cb.equal(root.get("bookingStatus"), bookingStatus));
                }
                if(StringUtil.isNoEmpty(bookingStartTime)){
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("bookingStartTime"), bookingStartTime));
                }
                if(StringUtil.isNoEmpty(bookingEndTime)){
                    predicateList.add(cb.lessThanOrEqualTo(root.get("bookingEndTime"), bookingEndTime));
                }
                if(StringUtil.isNoEmpty(bookingPhone)){
                    predicateList.add(cb.like(root.get("bookingPhone"), "%"+bookingPhone+"%"));
                }
                predicateList.add(cb.lessThanOrEqualTo(root.get("deleteTime"),0 ));
                Predicate[] pre = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(pre));
                return cb.and(predicateList.toArray(pre));
            }
        },pageable) ;
    }

    /**
     * 说明:五分钟执行一次，更新预约状态，判断是否逾期
     *
     * @param bookingStatus
     * @return
     * @author zhangxiaosan
     * @create 2021/4/11
     */
    @Override
    public List<TBooking> findByBookingStatus(Integer bookingStatus) throws Exception {
        return tBookingServiceDao.findByBookingStatus(bookingStatus);
    }

    /***
     * 根据id获取预约记录
     * @param id
     */
    @Override
    public TBooking getById(String id) throws Exception {
        return tBookingServiceDao.getOne(Long.valueOf(id));
    }

    /**
     * 说明: 删除一条记录
     *
     * @param id
     * @return
     * @author zhangxiaosan
     * @create 2021/4/16
     */
    @Override
    public int deleteOne(Integer id) throws Exception {
        TBooking one = tBookingServiceDao.getOne(Long.valueOf(id));
        one.setDeleteTime(Long.valueOf(DateUtil.getTimeStampNow()));
        TBooking save = tBookingServiceDao.save(one);
        if (save!=null)return 1;
        return 0;
    }

    /**
     * 说明: 根据核销码获取预约记录
     *
     * @param code 核销码
     * @return
     * @author zhangxiaosan
     * @create 2021/4/21
     */
    @Override
    public TBooking getByNumber(String code) throws Exception {
        return tBookingServiceDao.findByNumber(code);
    }
}
