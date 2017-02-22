package com.java.demo.basic;

import java.util.Date;

/**
 * <p></p>
 *
 * @author: xinyuan.ymm
 * @create: 2016-12-25 下午8:14
 */
public class DateTest {

    public static void main(String[] args) {
        Date dateFromUtil = new Date();
        java.sql.Date dateFromSql = new java.sql.Date(dateFromUtil.getTime());
        Date dateFromSqlDateConvert = new Date(dateFromSql.getTime());
        System.out.println(dateFromUtil);//正常的日期信息，包含毫秒数等信息
        System.out.println(dateFromSql);//只包含日期，比如 2016-12-25
        System.out.println(dateFromSqlDateConvert);

        System.out.println("OK");

    }
}
