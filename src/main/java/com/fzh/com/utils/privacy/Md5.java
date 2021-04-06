package com.fzh.com.utils.privacy;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5 {
    /**
     * 将字符串用MD5加密
     * 参数：字符串
     * 返回值：MD5加密后的字符串
     * */
    public static String encrypt(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定md5算法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        //加密字符串
        return base64Encoder.encodeToString(md5.digest(input.getBytes("utf-8")));
    }
}
