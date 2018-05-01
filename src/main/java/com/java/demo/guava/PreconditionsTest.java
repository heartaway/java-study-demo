package com.java.demo.guava;

import com.google.common.base.Preconditions;
import org.testng.annotations.Test;

/**
 * 受鉴于checkNotNull方法，JDK7中在Objects中引入了Objects.requireNonNull方法；
 *
 * @author: xinyuan.ymm
 * @create: 2017-02-16 下午2:43
 */
public class PreconditionsTest {

    @Test
    public void testPreConditions() {
        Integer i = 1, j = 2, k = 3;
        Boolean b = null;
        Preconditions.checkArgument(b == null);
        Preconditions.checkNotNull(b, " i = %s  j = %s k = %s", i, j, k);
    }
}
