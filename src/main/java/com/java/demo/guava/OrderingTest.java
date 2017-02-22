package com.java.demo.guava;

import com.google.common.collect.Ordering;
import org.testng.annotations.Test;

/**
 * @author: xinyuan.ymm
 * @create: 2017-02-18 上午11:18
 */
public class OrderingTest {

    @Test
    public void testOrder() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return 0;
            }
        };

    }
}
