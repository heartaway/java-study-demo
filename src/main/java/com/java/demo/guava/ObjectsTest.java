package com.java.demo.guava;

import com.google.common.base.MoreObjects;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author: xinyuan.ymm
 * @create: 2017-02-16 下午3:23
 */
public class ObjectsTest {

    public String name;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).toString();
    }

    @Test
    public void testObjects() {
        Date time = null;
        time = MoreObjects.firstNonNull(time, new Date());
        System.out.println(time);


        ObjectsTest objectsTest = new ObjectsTest();
        objectsTest.name = "yanmingming";
        System.out.println(objectsTest);
    }
}
