package com.java.demo.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>可重入的自旋锁</p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/6/3
 * Time: 下午9:14
 */
public class ReentrancySpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();
    private int count = 0;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            count++;
            System.out.println("可重入" + count);
            return;
        }

        while (!owner.compareAndSet(null, current)) {
        }
        System.out.println("获得锁");
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (current == owner.get()) {
            if (count != 0) {
                count--;
            } else {
                owner.compareAndSet(current, null);
            }
        }
        owner.compareAndSet(current, null);
        System.out.println("释放锁"+count);
    }


    public static void main(String[] args) {
        ReentrancySpinLock spinLock = new ReentrancySpinLock();
        spinLock.lock();
        System.out.println("再次调用");
        spinLock.lock();
        spinLock.unlock();
        System.out.println("结束");
    }

}
