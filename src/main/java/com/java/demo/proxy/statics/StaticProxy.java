package com.java.demo.proxy.statics;

/**
 * decorator 装饰器模式下的静态代理； 典型代表 Mybatis下的cache
 * 静态代理的三要素：
 * 1. 共同的接口；
 * 2. 真实对象；
 * 2. 代理对象，采用装饰器模式，代理对象持有真实对象的引用；
 *
 * 静态代理的弊端：
 * 1. 在真实对象比较多的情况下，需要创建同等数量的代理类，类数量扩大一倍；
 * 2. 真实对象方法变更时，代理对象都需要变更，增加了代码维护的复杂度；
 *
 * @author: heartaway
 * @create: 2018-04-09 上午10:56
 */
public class StaticProxy implements Action {

    private Action delegate;

    public StaticProxy(Action delegate) {
        this.delegate = delegate;
    }

    @Override
    public void doSomething() {
        System.out.println("delegate object do something");
        delegate.doSomething();
    }

    public static void main(String[] args) {
        Action action = new StaticProxy(new RealObject());
        action.doSomething();
    }
}
