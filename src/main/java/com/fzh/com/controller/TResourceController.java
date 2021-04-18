package com.fzh.com.controller;

import com.fzh.com.model.TResource;
import com.fzh.com.sevice.TResourceService;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "resourceApi")
public class TResourceController {
    @Autowired
    private TResourceService tResourceService;
    /***
     * 上传图片
     */
    @PostMapping(value = "/uploadFile" )
    public String uploadFile(HttpServletRequest request, String refId, String table, String fileCode){
        System.out.println("TResourceController uploadFile begin!");
        String resStr = "";
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "D://file//znbm//";
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            if (!file.isEmpty()) {
                try{
                    TResource resource = tResourceService.saveFile(file,refId,table,fileCode,null,filePath);
                    resStr = ResponseUtil.success(resource);
                }catch(Exception e){
                    System.out.println("TResourceController uploadFile error!"+e);
                    resStr = ResponseUtil.error("TResourceController uploadFile error!"+e);
                }

            } else {
                resStr = ResponseUtil.error("第 " + i + " 个文件上传失败因为文件为空");
                System.out.println("第 " + i + " 个文件上传失败因为文件为空");
            }
        }
        System.out.println("TResourceController uploadFile End!");
        return resStr;
    }

    /**
     * 查找图片
     * */
    @RequestMapping("/findFile")
    public String findFile(Integer id,String fileCode,String refTable){
        System.out.println("findFile is begin!");
        String resStr = "";
        try{
            List<TResource> list = tResourceService.findByCondition(id.toString(),refTable,"1",fileCode);
            resStr = ResponseUtil.success(list);
        }catch(Exception e){
            System.out.println("findFile error:" + e);
            resStr = ResponseUtil.error("异常 error:"+e);
        }
        System.out.println("findFile is end!");
        return resStr;
    }

    /**
    * 说明: 根据id查找文件，id为多个时用逗号分割
    * @author   zhangxiaosan
    * @create   2021/4/15
    * @param
    * @return
    */
    @RequestMapping("/findFileByIdList")
    public String findFileByIdList(String id,String fileCode,String refTable){
        System.out.println("findFile is begin!");
        if (StringUtil.isEmpty(id)) ResponseUtil.error("图片id不能为空");
        if (StringUtil.isEmpty(fileCode)) ResponseUtil.error("图片代码不能为空");
        if (StringUtil.isEmpty(refTable)) ResponseUtil.error("表名不能为空");
        String returnStr = "";
        try{
            String[] ids = id.split(",");
            if (ids.length <= 0){
                return ResponseUtil.error("图片id不能为空");
            }
            List resList = new ArrayList();
            for (String getId : ids){
                TResource attachment = tResourceService.findById(Integer.valueOf(getId));
                resList.add(attachment);
            }
            returnStr = ResponseUtil.success(resList);
        }catch(Exception e){
            System.out.println("findFile error:" + e);
            returnStr = ResponseUtil.error("异常 error:"+e);
        }
        System.out.println("findFile is end!");
        return returnStr;
    }


}
