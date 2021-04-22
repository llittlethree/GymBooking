package com.fzh.com.controller;

import com.fzh.com.model.TResource;
import com.fzh.com.sevice.TResourceService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.PageUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "resourceApi")
public class TResourceController {
    @Autowired
    private TResourceService tResourceService;


    @Value("${picPath}")
    private String picPath;

    /***
     * 上传图片
     * @param refId 关联的id
     * @param table 关联的表格
     * @param fileCode 文件代码
     * @param type 类型
     */
    @PostMapping(value = "/uploadFile" )
    public String uploadFile(HttpServletRequest request, String refId, String table, String fileCode,String type){
        System.out.println("TResourceController uploadFile begin!");
        String resStr = "";
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        System.out.println(files.size());
        //轮播
        try{
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                String filePath = picPath;
                File fileDir = new File(filePath);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                if (!file.isEmpty()) {
                    TResource resource = tResourceService.saveFile(file,refId,table,fileCode,null,filePath);
                    resStr = ResponseUtil.success(resource);
                } else {
                    resStr = ResponseUtil.error("第 " + i + " 个文件上传失败因为文件为空");
                    System.out.println("第 " + i + " 个文件上传失败因为文件为空");
                }
            }

            resStr = ResponseUtil.success("成功");
        }catch(Exception e){
            System.out.println("TResourceController uploadFile error!"+e);
            resStr = ResponseUtil.error("TResourceController uploadFile error!"+e);
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
    @RequestMapping(value = "list")
    public String list(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(value = "order",defaultValue = "desc") String order,
            @RequestParam(value = "orderBy",defaultValue = "useStatus") String orderBy,
            @RequestParam(value = "refId",required = false) String refId,
            @RequestParam(value = "refTable",required = false) String refTable,
            @RequestParam(value = "resourceCode",required = false) String resourceCode,
            @RequestParam(value = "useStatus",required = false) String useStatus
    ){
        String resStr="";
        try {
            String start = null,end = null;
            Pageable pageable = PageUtil.page(page, pageSize, order, orderBy);
            Page list = tResourceService.list(pageable, refId, refTable, resourceCode,useStatus);
            System.out.println(list);
            Map map = PageUtil.pageFormart(list);
            resStr = ResponseUtil.layuiTablePage(0, "成功", map.get("datas"),Integer.valueOf(map.get("total").toString()) );
        }catch (Exception e){
            resStr = ResponseUtil.layuiTablePage(1, "查询异常", e,0);
            e.printStackTrace();
        }
        System.out.println("tBooking list End!");
        return  resStr;
    }


    /**
    * 说明: 状态修改
    * @author   zhangxiaosan
    * @create   2021/4/22
    * @param
    * @return
    */
    @RequestMapping(value = "changeUseStatus")
    public String changeUseStatus(@RequestParam(value = "id")String id,@RequestParam(value = "useStatus")String useStatus){
        System.out.println("param id:"+id);
        System.out.println("param useStatus:"+useStatus);
        if(StringUtil.isEmpty(id))return ResponseUtil.error("id不能为空");
        if(StringUtil.isEmpty(useStatus))return ResponseUtil.error("状态不能为空");

        String resStr = "";
        try {
            TResource byId = tResourceService.findById(Integer.valueOf(id));
            if(byId==null)return ResponseUtil.error("找不到该资源");
            byId.setUseStatus(Integer.valueOf(useStatus));
            TResource tResource = tResourceService.save(byId);
            if(tResource!=null){
                resStr  = ResponseUtil.success(tResource);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resStr = ResponseUtil.error("changeUseStatus error:"+e);
        }
        return resStr;
    }

    /**
     * 软删除一条记录
     * */
    @RequestMapping(value="/deleteOne",method = RequestMethod.POST)
    @ResponseBody()
    public String deleteOne(
            @RequestParam(value ="id")  Integer id
    ){
        if(id==null||id <= 0)return ResponseUtil.error("id不能为空");
        String resStr = "";
        try {
            int res = tResourceService.deleteOne(id);
            resStr = ResponseUtil.success(res);
        } catch (Exception e) {
            resStr = ResponseUtil.error("异常："+e);
            e.printStackTrace();
        }
        return resStr;
    }
    /**
     *
     * 批量软删除
     * */
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
    @ResponseBody()
    public String deleteAll(
            @RequestParam(value ="ids")  String ids
    ){
        if(StringUtil.isEmpty(ids))return ResponseUtil.error("id不能为空");
        String resStr = "";
        try {
            System.out.println(ids);
            String[] ids_str = ids.split(",");
            System.out.println(ids_str);
            int res =0;
            for (String id:ids_str) {
                res = tResourceService.deleteOne(Integer.valueOf(id));
            }
            resStr = ResponseUtil.success(res);
        } catch (Exception e) {
            resStr = ResponseUtil.error("异常："+e);
            e.printStackTrace();
        }
        return resStr;
    }
}
