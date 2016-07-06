package com.java.demo.concurrent;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/6/3
 * Time: 下午8:36
 */
public class ReentrancyLock {

    public synchronized void invoke(String key) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        System.out.println(key+"调用父类的invoke方法");
    }

}
