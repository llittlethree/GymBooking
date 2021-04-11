package com.fzh.com.sevice.impl;

import com.fzh.com.model.TStudent;
import com.fzh.com.sevice.TStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fzh.com.dao.TStudentServiceDao;
/**
 * @author 张小三
 * @create 2021-03-24 22:55
 * @verson 1.0.0
 */
@Service
public class TStudentServiceImpl implements TStudentService {
    @Autowired
    private TStudentServiceDao tStudentServiceDao;


    /**
     * 说明: 学生登录，账号同时校验学号和手机号
     *
     * @param username 账号
     * @param password 密码
     * @return
     * @author zhangxiaosan
     * @create 2021/4/10
     */
    @Override
    public TStudent findByUserNameAndPassword(String username, String password) throws Exception {
        TStudent tStudent = tStudentServiceDao.findByStudentNumberAndStudentPassword(username,password);
        if (tStudent != null) return tStudent;
        tStudent = tStudentServiceDao.findByStudentPhoneAndStudentPassword(username,password);
        if (tStudent != null)   return tStudent;
        return null;

    }
}
