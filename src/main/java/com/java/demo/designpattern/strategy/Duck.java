package com.java.demo.designpattern.strategy;

/**
 * 策略模式：定义了算法蔟，封闭封装起来，让他们之间尅相互替换。
 * 使用场景：比如我在设计凑框迁移调度算法的时候， 拥有不同的匹配层（不同的算法蔟），通过接口形式定义算法蔟，在执行层的模板方法中动态调用具体的算法实现。
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-11 上午11:12
 */
public abstract class Duck {

    //把变化的部分单独独立出来，通过属性组合的方式，扩充超类行为
    protected FlyBehavior flyBehavior;

    public abstract void display();

    public void swim() {
        System.out.println("good duck");
    }

    //提供默认接口实现，这样每个之类就获得了此行为，并且通过自主设置不同的实现类对象，达到方法的动态调用
    public void performFly() {
        flyBehavior.fly();
    }

    //通过get、set方法，随时这是行为的实现类，可以随时切换对象行为
    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}
