package com.java.demo.designpattern.observer;

/**
 * 观察者模式:定义了对象中的一对多关系
 * 使用场景：比如订单状态更新，需要通过多个关心订单状态的系统，并把更新后的状态告诉他们。这里面使用最多的就是消息队列，也是一种最常用的松耦合的方式；
 * 比如使用消息队列松耦合订单系统与供应链系统，这两个系统的稳定性等级完全是不一致的，不能因为非核心系统影响了核心系统。
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-11 下午3:23
 */
public interface Subject {

    void registerObserver(WeathObserver weathObserver);

    void removeObserver(WeathObserver weathObserver);

    void notifyObservers();
}
