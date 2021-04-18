package com.fzh.com.sevice;

import com.fzh.com.model.TResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TResourceService {

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
    TResource saveFile(MultipartFile file, String refId, String table, String fileCode, Object o, String filePath) throws  Exception;

    /**
    * 说明:查找图片
    * @author   zhangxiaosan
    * @create   2021/4/15
    * @param    id
    * @param    refTable 外部关联的表明
    * @return
    */
    List<TResource> findByCondition(String id, String refTable, String s, String fileCode) throws  Exception;

    /***
     * 根据id 查找图片
     * @param valueOf
     * @return
     */
    TResource findById(Integer valueOf) throws  Exception;
}
