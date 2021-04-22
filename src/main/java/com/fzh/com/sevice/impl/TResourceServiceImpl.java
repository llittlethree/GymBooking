package com.fzh.com.sevice.impl;

import com.fzh.com.dao.TResourceDao;
import com.fzh.com.model.TBooking;
import com.fzh.com.model.TResource;
import com.fzh.com.sevice.TResourceService;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 张小三
 * @create 2021-04-15 10:53
 * @verson 1.0.0
 */
@Service
public class TResourceServiceImpl implements TResourceService {
    @Autowired
    private TResourceDao tResourceDao;
    /***
     * 上传文件
     * @param file 文件
     * @param refId 关联的外部id
     * @param table 关联的外部表名称
     * @param fileCode 文件码
     * @param o 原文件名
     * @param filePaths 文件路径
     * @return
     * @throws Exception
     */
    @Override
    public TResource saveFile(MultipartFile file, String refId, String table, String fileCode, Object o, String filePaths) throws Exception {
        System.out.println("saveFile is begin!");
        File fileDir = new File(filePaths);
        BufferedOutputStream stream = null;
        TResource tResource = null;
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {

            byte[] bytes = file.getBytes();

            String fileName = file.getOriginalFilename().toLowerCase();
            //获取最后一个.的位置
            int lastIndexOf = fileName.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String suffix = fileName.substring(lastIndexOf + 1);
            //String prefix = fileName.substring(0,lastIndexOf);
            //随机获取文件名
            String prefix = UUID.randomUUID().toString();
            fileName = prefix + "." + suffix;
            String filePath = filePaths + fileName;
            File file1 = new File(filePath);
            int i = 1;
            while (file1.exists()) {
                fileName = prefix + "_" + i + "." + suffix;

                filePath = filePaths + fileName;
                file1 = new File(filePath);
                i++;
            }
            System.out.println("fileName:" + fileName);
            //设置文件路径及名字
            stream = new BufferedOutputStream(new FileOutputStream(file1));
            stream.write(bytes);// 写入
            stream.close();

            //开始写入数据库
            tResource = tResourceDao.save(new TResource().setPath(filePath).setRefId(Long.valueOf(refId)).setRefTable(table).setResourceCode(fileCode).setUseStatus(1));

        } catch (Exception e) {
            System.out.println("saveFile error:" + e);
        }
        System.out.println("saveFile is end!");
        return tResource;
    }


    /*
    @Override
    public Attachment saveFileBase64(String base64, String refId, String table, String fileCode, String oriFileName, String filePaths) throws Exception {
        log.debug("saveFileBase64 is begin!");
        Attachment attachments = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        File fileDir = new File(filePaths);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            String prefix = sdf.format(new Date());
            String suffix = ImageConvert.getSuffix(base64);
            String fileName = prefix + "." + suffix;
            String filePath = filePaths + fileName;
            File file1 = new File(filePath);
            int i = 1;
            while (file1.exists()) {
                fileName = prefix + "_" + i + "." + suffix;
                filePath = filePaths + fileName;
                file1 = new File(filePath);
                i++;
            }

            String regString = "/home/zhns/";
            String showPath = filePaths.substring(filePaths.indexOf(regString) + regString.length() - 1)+fileName;

            ImageConvert.GenerateImage(base64.split(",")[1], filePath);
            Attachment attachment = new Attachment();
            attachment.setRefId(refId);
            attachment.setRefTable(table);
            attachment.setFileName(prefix);
            attachment.setFileCode(fileCode);
            attachment.setOriFileName(oriFileName);
            attachment.setFilePath(showPath);
            attachment.setFileType(suffix);
            attachment.setStatus("1");
            attachment.setCreateTime(new Date());
            attachments = save(attachment);

        } catch (Exception e) {
            System.out.println("saveFileBase64 error:" + e);
        }
        System.out.println("saveFileBase64 is end!");
        return attachments;
    }*/

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
        return tResourceDao.findById(Long.valueOf(valueOf)).get();
    }

    /**
     * 说明: 根据表明和文件编码查找文件
     *
     * @param table
     * @param fileCode
     * @return
     * @author zhangxiaosan
     * @create 2021/4/22
     */
    @Override
    public List<TResource> findByTableAndCode(String table, String fileCode) throws Exception {
        return tResourceDao.findByRefTableAndResourceCode(table,fileCode);
    }

    /**
     * 说明: 根据id删除
     *
     * @param tResource
     * @return
     * @author zhangxiaosan
     * @create 2021/4/22
     */
    @Override
    public Integer delete(TResource tResource) throws Exception {
        try{
            Long id = tResource.getId();
            Integer res = tResourceDao.delById(id);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 说明: 查找资源列表
     *
     * @param pageable
     * @param refId        关联的id
     * @param refTable     关联的表格
     * @param resourceCode 资源代码
     * @param useStatus    使用状态 1可用 0不可用
     * @return
     * @author zhangxiaosan
     * @create 2021/4/22
     */
    @Override
    public Page list(Pageable pageable, String refId, String refTable, String resourceCode, String useStatus) throws Exception {
        return tResourceDao.findAll(new Specification<TResource>() {
            @Override
            public Predicate toPredicate(Root<TResource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if(StringUtil.isNoEmpty(refId)){
                    predicateList.add(cb.equal(root.get("refId"), refId));
                }
                if(StringUtil.isNoEmpty(refTable)){
                    predicateList.add(cb.equal(root.get("refTable"), refTable));
                }
                if(StringUtil.isNoEmpty(resourceCode)){
                    predicateList.add(cb.equal(root.get("resourceCode"), resourceCode));
                }
                if(StringUtil.isNoEmpty(useStatus)){
                    predicateList.add(cb.equal(root.get("useStatus"), useStatus));
                }

                Predicate[] pre = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(pre));
                return cb.and(predicateList.toArray(pre));
            }
        },pageable) ;
    }

    /***
     * 修改
     * @param tResource
     * @return
     * @throws Exception
     */
    @Override
    public TResource save(TResource tResource) throws Exception {
        return tResourceDao.save(tResource);
    }

    /**
     * 说明: 删除
     *
     * @param id
     * @return
     * @author zhangxiaosan
     * @create 2021/4/22
     */
    @Override
    public Integer deleteOne(Integer id) throws Exception {
        System.out.println("------"+id);
        return tResourceDao.delById(Long.valueOf(id));
    }
}
