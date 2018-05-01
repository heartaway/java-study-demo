package com.java.demo.guava;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-17 上午12:48
 */
public class Print {


    public static void out(Object out) {
        System.out.println(out.toString());
    }

    public static void out(Object[] objects) {
        Preconditions.checkNotNull(objects);
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }

    public static void out(List<Object> objectList) {
        Preconditions.checkNotNull(objectList);
        objectList.forEach(object -> System.out.println(object.toString()));
    }


}
