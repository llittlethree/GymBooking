package com.fzh.com.sevice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 张小三
 * @create 2021-03-24 22:53
 * @verson 1.0.0
 */
public interface TAdminService {
    /**
     * 说明: 分页查找管理员
     * @author   zhangxiaosan
     * @create   2021/3/25
     * @param pageable Pageable 当前页码
     * @param adminPhone 手机号
     * @param adminName 名称
     * @param adminSex 性别
     * @param createStartTime 开始时间
     * @param createEndTime 结束时间
     * @param adminStatus 状态
     * @return
     */
    Page list(Pageable pageable, String adminPhone, String adminName, String adminSex, String createStartTime, String createEndTime, String adminStatus) throws Exception;
}
