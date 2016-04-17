package com.java.demo.classcreate;

/**
 * <p></p>
 * User: 心远
 * Date: 14/12/16
 * Time: 上午1:01
 */
public enum SingleClassV2 {

    INSTANCE;

    SingleClassV2() {
    }

    public void methods(String args) {

    }

    public static void main(String[] args) {
        SingleClassV2.INSTANCE.methods("测试单例");
    }
}
