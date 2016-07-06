package com.java.demo.classcreate;

/**
 * <p>可见性</p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/5/21
 * Time: 上午1:42
 */
public class CanSee {

    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }


    public static void main(String[] args) {

    }

}
