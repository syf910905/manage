package com.demo.yunfei.demo;

import java.time.*;

/**
 * @author : yunfei
 * @date : 2018/11/22 9:17
 */
public class LocalTimeUtils {

    /**
     * 工具LocalDate创建方式 默认(yyyy-MM-dd)
     * 同理 LocalDateTime，
     */
    public static void localDate() {
        //当前年月日
        LocalDate now = LocalDate.now();
        System.out.println(now);
        //指定年月日
        now = LocalDate.of(2014, 01, 01);
        System.out.println(now);
        now = LocalDate.of(2018, Month.SEPTEMBER, 10);
        System.out.println(now);
        //指定时区
        now = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(now);
        //指定年份内的指定天数
        now = LocalDate.ofYearDay(2018, 100);
        System.out.println(now);
    }


    /**
     * LocalDateTime
     */
    public static void localDateTime(){
        LocalDateTime now = LocalDateTime.now();
        now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        now=LocalDateTime.of(2018,10,12,12,30,20);
    }


    public static void main(String[] args) {
        localDate();
    }

}
