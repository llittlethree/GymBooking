package com.fzh.com.sevice.impl;

import com.fzh.com.sevice.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
