package com.fzh.com.sevice;

import com.fzh.com.model.TStudent;

public interface TStudentService {
    /**
    * 说明: 学生登录，账号同时校验学号和手机号
    * @author   zhangxiaosan
    * @create   2021/4/10
    * @param username 账号
    * @param    password 密码
    * @return
    */
    TStudent findByUserNameAndPassword(String username, String password) throws  Exception;
}
