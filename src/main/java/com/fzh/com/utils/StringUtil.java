package com.fzh.com.utils;

import java.util.regex.Pattern;

public class StringUtil {
    public static Boolean isEmpty(String str){
        if (str==null || str.trim().isEmpty() ||  "".equals(str.trim()) || "null".equals(str.trim())){
            return true;
        }
        return false;
    }

    public static  Boolean isNoEmpty(String str){
        return !isEmpty(str);
    }


    /***
     * 判断是否为整数数字
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(isInteger("1.0"));
        System.out.println(4%500);
    }

}
