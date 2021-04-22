package com.fzh.com.sevice;

import com.fzh.com.model.TResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
    * 说明: 根据表明和文件编码查找文件
    * @author   zhangxiaosan
    * @create   2021/4/22
    * @param
    * @return
    */
    List<TResource> findByTableAndCode(String table, String fileCode)throws  Exception;

    /**
    * 说明: 根据id删除
    * @author   zhangxiaosan
    * @create   2021/4/22
    * @param
    * @return
    */
    Integer delete(TResource tResource)throws  Exception;

    /**
     * 说明: 查找资源列表
     * @author   zhangxiaosan
     * @create   2021/4/22
     * @param refId 关联的id
     * @param refTable 关联的表格
     * @param resourceCode 资源代码
     * @param useStatus 使用状态 1可用 0不可用
     * @return
     */
    Page list(Pageable pageable, String refId, String refTable, String resourceCode, String useStatus)throws  Exception;


    /***
     * 修改
     * @param tResource
     * @return
     * @throws Exception
     */
    TResource save(TResource tResource) throws  Exception;

    /**
    * 说明: 删除
    * @author   zhangxiaosan
    * @create   2021/4/22
    * @param
    * @return
    */
    Integer deleteOne(Integer id) throws  Exception;
}
