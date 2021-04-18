package com.fzh.com.sevice.impl;

import com.fzh.com.model.TAdmin;
import com.fzh.com.sevice.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fzh.com.dao.TAdminServiceDao;
/**
/**
 * @author 张小三
 * @create 2021-03-24 22:54
 * @verson 1.0.0
 */
@Service
public class TAdminServiceImpl  implements TAdminService {
    @Autowired
    private TAdminServiceDao tAdminServiceDao;

    /**
     * 说明: 分页查找管理员
     *
     * @param pageable        Pageable 当前页码
     * @param adminPhone      手机号
     * @param adminName       名称
     * @param adminSex        性别
     * @param createStartTime 开始时间
     * @param createEndTime   结束时间
     * @param adminStatus     状态
     * @return
     * @author zhangxiaosan
     * @create 2021/3/25
     */
    @Override
    public Page list(Pageable pageable, String adminPhone, String adminName, String adminSex, String createStartTime, String createEndTime, String adminStatus) throws Exception {
        return null;
    }

    /**
     * 说明: 登录
     *
     * @param phone
     * @param password
     * @param status
     * @return
     * @author zhangxiaosan
     * @create 2021/4/15
     */
    @Override
    public TAdmin findByAdminPhoneAndAdminPasswordAndAdminStatus(String phone, String password, Integer status) throws Exception {
        return tAdminServiceDao.findByAdminPhoneAndAdminPasswordAndAdminStatus(phone,password,status);
    }
}
