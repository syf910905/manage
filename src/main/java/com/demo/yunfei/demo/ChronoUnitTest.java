package com.demo.yunfei.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author : yunfei
 * @date : 2018/11/22 9:06
 */
public class ChronoUnitTest {



    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        LocalDateTime qq = ChronoUnit.DAYS.addTo(LocalDateTime.now(), 1);
        System.out.println(qq);
    }
}
