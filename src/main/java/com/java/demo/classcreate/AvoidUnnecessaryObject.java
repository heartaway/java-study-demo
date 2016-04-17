package com.java.demo.classcreate;

import java.util.Date;

/**
 * <p>避免创建不必要的对象</p>
 * User: 心远
 * Date: 14/12/16
 * Time: 上午1:50
 */
public class AvoidUnnecessaryObject {

    public static void main(String[] args) {
        Long sum = 0L;
        Date start = new Date();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());

        long sumLittle = 0L;
        Date startNext = new Date();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sumLittle += i;
        }
        Date endNext = new Date();
        System.out.println(endNext.getTime() - startNext.getTime());
        //8533 ， 722 可见一个小小的类型自动装箱，在大量计算的情况下带来的时间差距是差不多10倍以上，在内存使用上的差距更大，所以优先使用基本类型

    }
}
