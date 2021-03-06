package com.fzh.com.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fzh.com.model.TStudent;
import com.fzh.com.model.TUserVo;
import com.fzh.com.sevice.TStudentService;
import com.fzh.com.utils.JwtUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import com.fzh.com.utils.WxLoginInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张小三
 * @create 2021-03-24 22:51
 * @verson 1.0.0
 */
@RestController
@RequestMapping(value = "studentApi")
public class TStudentController {
    @Autowired
    private TStudentService tStudentService;

    //学生登录
    @RequestMapping(value = "studentLogin")
    public String studentLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "code") String code
    ){
        System.out.println("studentLogin start");
        if(StringUtil.isEmpty(username)) return ResponseUtil.error("账号不能为空");
        if(StringUtil.isEmpty(password)) return ResponseUtil.error("密码不能为空");
        if(StringUtil.isEmpty(code)) return ResponseUtil.error("code不能为空");
        String resStr ="";
        try{
            TStudent tStudent = tStudentService.findByUserNameAndPassword(username,password);
            if (tStudent != null){
                String sessionKey = null,openId = null;
                try {
                    String openidAndSessionkey = WxLoginInfoUtil.getOpenidAndSessionkey(code);
                    JSONObject jsonObject = JSONObject.parseObject(openidAndSessionkey);
                    sessionKey = jsonObject.getString("session_key");
                    openId = jsonObject.getString("openid");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }

                resStr = ResponseUtil.success(
                    new TUserVo()
                    .setClassId(tStudent.getClassId())
                    .setCreateTime(tStudent.getCreateTime())
                    .setUpdateTime(tStudent.getUpdateTime())
                    .setDeleteTime(tStudent.getDeleteTime())
                    .setId(tStudent.getId())
                    .setOpenid(tStudent.getOpenid())
                    .setRemark(tStudent.getRemark())
                    .setPhone(tStudent.getStudentPhone())
                    .setName(tStudent.getStudentName())
                    .setStudentNumber(tStudent.getStudentNumber())
                    .setSex(tStudent.getStudentSex())
                    .setStatus(tStudent.getStudentStatus())
                    .setSign(0)
                    .setSessionKey(sessionKey)
                    .setOpenid(openId)
                );
            }
            if (tStudent == null) resStr = ResponseUtil.error("登录失败，请检查是否已经注册或账号密码是否正确");
        }catch (Exception e){
            resStr = ResponseUtil.error("StudentLogin error:"+e);
            e.printStackTrace();
        }
        System.out.println("studentLogin end");
        return resStr;
    }
}
