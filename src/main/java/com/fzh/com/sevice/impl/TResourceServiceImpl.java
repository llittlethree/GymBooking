package com.fzh.com.sevice.impl;

import com.fzh.com.model.TResource;
import com.fzh.com.sevice.TResourceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 张小三
 * @create 2021-04-15 10:53
 * @verson 1.0.0
 */
@Service
public class TResourceServiceImpl implements TResourceService {
    /***
     * 上传文件
     * @param file 文件
     * @param refId 关联的外部id
     * @param table 关联的外部表名称
     * @param fileCode 文件码
     * @param o 原文件名
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    @Override
    public TResource saveFile(MultipartFile file, String refId, String table, String fileCode, Object o, String filePath) throws Exception {
        return null;
    }

    /**
     * 说明:查找图片
     *
     * @param id
     * @param refTable 外部关联的表明
     * @param s
     * @param fileCode
     * @return
     * @author zhangxiaosan
     * @create 2021/4/15
     */
    @Override
    public List<TResource> findByCondition(String id, String refTable, String s, String fileCode) throws Exception {
        return null;
    }

    /***
     * 根据id 查找图片
     * @param valueOf
     * @return
     */
    @Override
    public TResource findById(Integer valueOf) throws Exception {
        return null;
    }
}
