package com.fzh.com.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 处理时间的工具类
 * */
public class DateUtil {

    /**
     * 获取当前的时间戳
     * @param
     * @return 10位时间戳 int
     * */
    public static Integer getTimeStampNow(){
        return (int) (System.currentTimeMillis() / 1000);
    }
    /**
     * 获取当前的时间戳
     * @param
     * @return 13位时间戳 int
     * */
    public static Long getTimeStampNowLong(){
        return System.currentTimeMillis();
    }

    /**
     * 13位时间戳转成10位时间戳
     * @param longTimeStamp 13位时间戳
     * */
    public static Integer LongTimeStampToTimeStamp(Long longTimeStamp){
        return (int)(longTimeStamp/1000);
    }

    /**
    * 说明: 获取当前时间的指定格式
    * @author   zhangxiaosan
    * @create   2021/4/11
    * @param type String 格式
    * @return
    */
    public static String getTimeType(String type){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(type));
    }

    /**
     * 字符串时间格式转换成时间格式
     * String --> Date
     * */
    public static Date stringDateFormatToDate(String stringDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            try {
                date = simpleDateFormat1.parse(stringDate);
            } catch (ParseException ex) {
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//注意月份是MM
                try {
                    date = simpleDateFormat2.parse(stringDate);
                } catch (ParseException exc) {
                    SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd");//注意月份是MM
                    try {
                        date = simpleDateFormat3.parse(stringDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        //System.out.println(date);   //Mon Sep 02 00:00:00 CST 2019
        //System.out.println(simpleDateFormat.format(date));  //2019-09-02
        return date;
    }

    /***
     * 字符串时间转成10位时间戳
     */
    public static Integer dateToTimeStamp(String  date){
        return Integer.valueOf(
                String.valueOf(
                        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .parse(date,new ParsePosition(0)).getTime()/1000)
        );
    }
    /***
     * 字符串时间转成13位时间戳
     */
    public static Long dateToTimeStampLong(String  date){
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .parse(date,new ParsePosition(0)).getTime()/1000;
    }

    /**
     * 时间计算。 根据计算标识计算目标时间
     * @param sign String 计算方式的标识
     * beforeDay7 七天前
     * beforeDay15 15天前
     * beforeMonth1 1个月前
     * beforeMonth3 3个月前
     * beforeMonth6 6个月前
     * beforeYear1 1年前
     * thisMonth 本月
     * nextMonth1 下1个月
     * nextMonth2 下2个月
     * nextMonth3 下3个月
     * beforeHours6 六小时前(近六小时)
     * */
    private static String calculationDay(String sign){
        LocalDateTime now = LocalDateTime.now();
        String fomater="yyyy-MM-dd HH:mm:ss";
        String nowday = now.format(DateTimeFormatter.ofPattern(fomater));
        String strtime = nowday;
        switch (sign){
            case "beforeDay7": //七天前
                strtime = now.minusDays(7).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "beforeDay15": //15天前
                strtime = now.minusDays(15).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "beforeMonth1": //1个月前
                strtime = now.minusMonths(1).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "beforeMonth3": //3个月前
                strtime = now.minusMonths(3).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "beforeMonth6": //6个月前
                strtime = now.minusMonths(6).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "beforeYear1": //1年前
                strtime = now.minusYears(1).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "thisMonth": //本月
                strtime = now.plusMonths(0).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "nextMonth1"://下1个月
                strtime = now.plusMonths(1).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "nextMonth2"://下2个月
                strtime = now.plusMonths(2).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "nextMonth3"://下3个月
                strtime = now.plusMonths(3).format(DateTimeFormatter.ofPattern(fomater));
                break;
            case "beforeHours6"://六小时前(近六小时)
                strtime = now.minusHours(6).format(DateTimeFormatter.ofPattern(fomater));
                break;
        }
        return strtime;
    }

    public static void main(String[] args) {
       /*System.out.println(getTimeStampNow());
        System.out.println(getTimeStampNowLong());
        System.out.println(LongTimeStampToTimeStamp(getTimeStampNowLong()));*/
        //stringDateFormatToDate("2020/12/30");
        System.out.println(calculationDay("beforeHours6"));
    }
}
