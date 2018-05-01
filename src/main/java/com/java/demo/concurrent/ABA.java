package com.java.demo.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/7/7
 * Time: 下午2:50
 */
public class ABA {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<Integer>(0, 0);

    public static void main(String[] args) throws Exception {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Thread thread1 = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(0, 1);
                atomicInteger.compareAndSet(1, 0);
            }
        });

        Thread thread2 = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    boolean result = atomicInteger.compareAndSet(0, 1);
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Thread threadR1 = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                stampedReference.compareAndSet(0, 1, stampedReference.getStamp(), stampedReference.getStamp() + 1);
                stampedReference.compareAndSet(1, 0, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            }
        });

        Thread threadR2 = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                try {
                    int stamp = stampedReference.getStamp();
                    TimeUnit.SECONDS.sleep(1);
                    boolean result = stampedReference.compareAndSet(0, 1, stamp, stamp + 1);
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        threadR1.start();
        threadR2.start();
    }
}
