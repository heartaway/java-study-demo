package com.java.demo.thread;

import org.testng.annotations.Test;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/18
 * Time: 下午5:40
 */
public class ThreadTest {

    /**
     * 当前线程的优先级会携带到新创建的线程优先级
     * 所以我们在创建新线程的时候,如果不期望主线程的优先级带入新创建的线程,那么就需要覆盖掉线程优先级
     */
    @Test
    public void testThreadPriority() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        Thread thread = new Thread("test-thread-priority");
        System.out.println(thread.getPriority());
    }

    /**
     * 可以看出守护线程创建出来的线程也是守护线程
     * 所以我们在创建新线程的时候,如果不期望主线程的deamon携带到新线程,那么久应该覆盖到deamon的设置
     */
    @Test
    public void testThreadDeamon() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread subThread = new Thread("test-thread-deamon");
                System.out.println(subThread.isDaemon());
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    /**
     * 测试 start 方法
     */
    @Test
    public void testStartMethod() {

    }


}
