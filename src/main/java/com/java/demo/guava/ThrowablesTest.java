package com.java.demo.guava;

import com.google.common.base.Throwables;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * 使用场景：比如我们定义了业务异常，但是我们调用的接口中只抛出了Exception，此时我们就可以对我们关心的业务异常进行处理，不关心的则继续向上传播
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-06 下午8:18
 */
public class ThrowablesTest {

    @Test
    public void testExceptionWillNotBeThrow() throws Throwable {
        try {
            throw new IllegalArgumentException("i am is illegalArgument");
        } catch (Throwable t) {
            Throwables.throwIfInstanceOf(t, IOException.class);
            System.out.println("here is will show.");
        }
    }


    /**
     * 如果异常类型是IO异常，直接跑出IO异常，如果不是，继续执行
     *
     * @throws Throwable
     */
    @Test
    public void testExceptionIsIOException() throws Throwable {
        try {
            throw new IOException("i am io exception");
        } catch (Throwable t) {
            Throwables.throwIfInstanceOf(t, IOException.class);
            System.out.println("here is not be show.");
        }
    }

    /**
     * propagateIfPossible = throwIfInstanceOf + throwIfUnchecked
     * 如果是IO异常，直接跑出IO异常，如果不是，则抛出Runtime异常或者Error
     *
     * @throws Throwable
     */
    @Test
    public void testExceptionWillBeThrow() throws Throwable {
        try {
            throw new IllegalArgumentException("i am is illegalArgument");
        } catch (Throwable t) {
            Throwables.propagateIfPossible(t, IOException.class);
            System.out.println("here is not be show.");
        }
    }

}
