package com.java.demo.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/7/27
 * Time: 下午3:57
 */
public class UnSafeInstance {

    public class UnSafeObject {

        public UnSafeObject() {
            //不会打印出，因为unsafe.allocateInstance初始化，不会调用构造器
            System.out.println("UnSafeObject 实例化");
        }
    }

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
        unsafe.allocateInstance(UnSafeObject.class);
        StringBuilder sb=new StringBuilder("abc");

        sb.reverse();
    }
}
