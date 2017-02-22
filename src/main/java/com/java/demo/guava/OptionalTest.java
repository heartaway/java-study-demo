package com.java.demo.guava;

import com.google.common.base.Optional;
import org.testng.annotations.Test;

/**
 * Optional 用于区分 null-object 和 空引用，用来强制提醒开发者，注意对Null的判断。迫使你积极思考引用缺失的情况。
 * 设计目的：设计Optional类的目的并不是完全取代null, 它的目的是设计更易理解的API
 * 使用场景：
 * 1. 用于可能返回null对象的方法返回值；
 *
 * @author: xinyuan.ymm
 * @create: 2017-02-16 上午10:30
 * @Since 1.8
 */
public class OptionalTest {

    private String name;

    public OptionalTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test

    public void testNull() {
        int age;
        age = 0;
        System.out.println(age);

        Object obj = null;//null标识不确定的对象
        System.out.println(obj);
        System.out.println(obj instanceof Object);
    }

    @Test
    public void testOptional() throws Exception {
        Optional nullOptional = Optional.fromNullable(null);
        System.out.println(nullOptional.isPresent());
        System.out.println(nullOptional.orNull());
        //java.util.Optinoal 并没有序列化（不适合作为对象属性），Guava 中 Optional 实现了序列化
    }
}
