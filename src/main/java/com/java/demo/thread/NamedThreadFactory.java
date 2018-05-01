package com.java.demo.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/18
 * Time: 下午5:59
 */
public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final boolean threadDeamon;

    NamedThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        String newname = name != null ? name + "-thread" : "thread";
        namePrefix = "pool-" + poolNumber.getAndIncrement() + "-" + newname + "-";
        threadDeamon = false;
    }

    NamedThreadFactory(String name, boolean deamon) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        String newname = name != null ? name : "thread";
        namePrefix = "pool-" + poolNumber.getAndIncrement() + "-" + newname + "-";
        threadDeamon = deamon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
        thread.setDaemon(threadDeamon);

        if (thread.getPriority() != Thread.NORM_PRIORITY) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                System.out.println("tagTask." + thread.getName() + " thrown an exception : " + ex);
            }
        });

        return thread;
    }
}
