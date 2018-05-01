package com.java.demo.proxy.statics;

/**
 * @author: heartaway
 * @create: 2018-04-09 上午10:55
 */
public class RealObject implements Action {

    @Override
    public void doSomething() {
        System.out.println("real object do something");
    }
}
