package com.java.demo.treadlocal;

/**
 * <p></p>
 *
 * @author: xinyuan.ymm
 * @create: 2016-11-16 下午8:44
 */
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal threadLocal = new ThreadLocal();
        private ThreadLocal threadLocal2 = new ThreadLocal();
        private String localParam = new String();

        @Override
        public void run() {
            int value = (int) (Math.random() * 100D);
            System.out.println(value);
            localParam = String.valueOf(value);
            threadLocal.set(value);
            threadLocal2.set(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println("threadLocal:" + threadLocal.get());
            System.out.println("localParam:" + localParam);
        }
    }


    /**
     * 我们很明显的看到了如果我们使用普通的内部变量，多个线程对变量的修改变量的修改会被覆盖，然后使用threadLocal却没有
     *
     * @param args
     */
    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();
    }



}
