package com.java.demo.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p> 多个非静态方法为 同步方法</p>
 * <p>
 * 结论：
 *   静态synchronized方法之间相互阻塞；
 *   非静态synchronized方法之间相互阻塞；
 *   静态synchronized与非静态synchronized之间不阻塞；
 * <p/>
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/6/2
 * Time: 下午8:39
 */
public class SynchronizedMoreMethod {

    public synchronized void method1() {
        try {
            Thread.sleep(3000);
            System.out.println("同步方法1执行");
        } catch (Exception e) {
        }
    }

    public synchronized void method2() {
        try {
            System.out.println("同步方法2执行");
        } catch (Exception e) {
        }
    }

    public synchronized static void method3() {
        try {
            Thread.sleep(3000);
            System.out.println("同步方法3执行");
        } catch (Exception e) {
        }
    }

    public synchronized static void method4() {
        try {
            System.out.println("同步方法4执行");
        } catch (Exception e) {
        }
    }

    public void method5() {
        try {
            System.out.println("同步方法5执行");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        final SynchronizedMoreMethod synchronizedMoreMethod = new SynchronizedMoreMethod();
        Executor executor = Executors.newFixedThreadPool(10);
        Runnable task1 = new Runnable() {
            public void run() {
                synchronizedMoreMethod.method1();
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {
                synchronizedMoreMethod.method2();
            }
        };
        Runnable task3 = new Runnable() {
            public void run() {
                synchronizedMoreMethod.method3();
            }
        };
        Runnable task4 = new Runnable() {
            public void run() {
                synchronizedMoreMethod.method4();
            }
        };

        Runnable task5 = new Runnable() {
            public void run() {
                synchronizedMoreMethod.method5();
            }
        };
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);

        //可以看出虽然是两个线程调用方法，但是task1在调用method1 的时候 task2调用method2被阻塞了，直到 task1完成method1的调用
        //相互组合的为 非静态的synchronized方法，非synchronized方法不受影响；
    }


}
