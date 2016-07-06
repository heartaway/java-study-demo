package com.java.demo.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>
 * Java 锁的重入性：
 * 由于非静态方法锁锁的是当前对象的引用，也即this，
 * 如果没有Java锁的可重入性，当一个线程获取ChildReentrancyLock的 invoke()代码块的锁后，
 * 这个线程已经拿到了 ChildReentrancyLock 的锁，当调用父类中的 invoke()方法的时，
 * JVM会认为这个线程已经获取了 ChildReentrancyLock 的锁，而不能再次获取，
 * 从而无法调用 ReentrancyLock 的 invoke()方法，从而造成死锁。
 * <p/>
 * 所谓的可重入性，可以理解为：当线程试图获得它自己占有的锁时，请求会成功。
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/6/3
 * Time: 下午8:37
 */
public class ChildReentrancyLock extends ReentrancyLock {
    @Override
    public synchronized void invoke(String key) {
        System.out.println(key + "调用子类的invoke方法");
        super.invoke(key);
    }
    public static void main(String[] args) {
        //测试1：验证锁的重入性，synchronized 具有重入锁；
        final ChildReentrancyLock childReentrancyLock = new ChildReentrancyLock();
        childReentrancyLock.invoke("");
        //测试2：如果锁只是基于对象this且具有重入性的话，那么不同线程使用同一个对象，可以同时进入一个同步方法（这样弊端显而易见），但是实际上是不会的；
        // 因为 “重进入的实现是通过为每个锁关联一个请求计数和一个占有它的线程” 。
        Executor executor = Executors.newFixedThreadPool(10);
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                childReentrancyLock.invoke("任务1");
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                childReentrancyLock.invoke("任务2");
            }
        };
        executor.execute(task1);
        executor.execute(task2);
    }
}
