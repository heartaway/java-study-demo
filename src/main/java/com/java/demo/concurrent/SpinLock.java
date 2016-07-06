package com.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>非可重入的自旋锁</p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/6/3
 * Time: 下午9:05
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!owner.compareAndSet(null, current)) {
        }
        System.out.println("获得锁");
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        owner.compareAndSet(current, null);
        System.out.println("释放锁");
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        spinLock.lock();
        spinLock.lock();
        spinLock.unlock();
        //可以看到 第一次调用Lock的时候， spinLock获得了该锁，第二次再次调用，却得不到锁，从而导致产生了死锁；
    }
}
