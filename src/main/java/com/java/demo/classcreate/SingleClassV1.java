package com.java.demo.classcreate;

/**
 * <p>单例</p>
 * User: 心远
 * Date: 14/12/16
 * Time: 上午12:57
 */
public class SingleClassV1 {

    private static SingleClassV1 INSTANCE = new SingleClassV1();

    public static SingleClassV1 getINSTANCE() {
        return INSTANCE;
    }
}
