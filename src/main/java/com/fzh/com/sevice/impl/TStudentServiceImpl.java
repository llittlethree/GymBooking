package com.fzh.com.sevice.impl;

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

}
