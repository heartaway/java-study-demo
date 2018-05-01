package com.java.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/5/5
 * Time: 上午10:29
 */
public class StaticVarVisibility {

    public static boolean shareVar = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                //JVM优化导致，当频繁使用主存变量的时候只做use，并未做read-load-use
                while (shareVar) {
                    i++;
                    //循环1000次，进行new Object();
                    //synchronized (this) { } 会强制刷新主内存的变量值到线程栈?我认为不是，是因为同步操作是一个耗时操作，所以对is的使用从单纯的use变为read-load-use
                    System.out.println(i); //println 是synchronized 的
                    //try {
                    //sleep操作释放了CPU，所以遵循JVM优化基准，尽可能保证工作内存和主存的及时同步，如果CPU一直被占用，就无法及时做到数据同步
                    //TimeUnit.MICROSECONDS.sleep(1);
                    //} catch (InterruptedException e) {
                    //}
                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        new Thread(new Runnable() {
            public void run() {
                shareVar = false;  //设置shareVar为false，使上面的线程结束while循环
            }
        }).start();
    }
}
