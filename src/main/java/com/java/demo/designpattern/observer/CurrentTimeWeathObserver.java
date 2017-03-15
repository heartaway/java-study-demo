package com.java.demo.designpattern.observer;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-11 下午3:38
 */
public class CurrentTimeWeathObserver implements WeathObserver {

    //保留对Subject的应用，不仅是为了注册使用，而且还为取消订阅使用，当然并不是前置要求
    //但是通过属性获取subject的引用，不能解决一个观察者同时观察不同多个主题的情况
    private Subject subject;

    public CurrentTimeWeathObserver(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("current time update");
    }
}
