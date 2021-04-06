package com.fzh.com.sevice.impl;

import com.fzh.com.sevice.TClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fzh.com.dao.TClassServiceDao;
/**
 * @author 张小三
 * @create 2021-03-24 22:54
 * @verson 1.0.0
 */
@Service
public class TClassServiceImpl  implements TClassService {
    @Autowired
    private TClassServiceDao tClassServiceDao;
}
