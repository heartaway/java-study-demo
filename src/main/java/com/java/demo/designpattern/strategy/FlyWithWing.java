package com.java.demo.designpattern.strategy;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-11 上午11:14
 */
public class FlyWithWing implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("i fly wing");
    }
}
