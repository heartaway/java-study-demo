package com.java.demo.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * java.util.Observable Observer 同样提供了观察者模式的两个类
 * java.util.Observable 的黑暗面：
 * 1. Observable 是一个类，而非一个接口，破坏了我们的"针对接口变成"的原则；
 * 2. Observable的使用必须使用继承（setChanged方法为protected），破坏了"多用组合少用继承"的原则
 * <p>
 * 使用场景：
 * GUI中的对象监听器，我们往往需要在一个对象被点击的时候，通知所有监听了此点击事件的函数，比如我之前做的报表框架。
 *
 * @author: xinyuan.ymm
 * @create: 2017-03-11 下午3:37
 */
public class WeathData implements Subject {

    private List<WeathObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(com.java.demo.designpattern.observer.WeathObserver weathObserver) {
        if (!observers.contains(weathObserver)) {
            observers.add(weathObserver);
        }
    }

    @Override
    public void removeObserver(com.java.demo.designpattern.observer.WeathObserver weathObserver) {
        if (observers.contains(weathObserver)) {
            observers.remove(weathObserver);
        }
    }

    @Override
    public void notifyObservers() {
        //从java.util.Observable.notifyObservers(java.lang.Object)方法中，我们可以学习到他考虑的并发的场景，增加删除观察者都会严格进行同步操作
        //但是通知变更没有进行同步操作，我猜想的原因是，我们并不知道外部调用时会耗时多久，如果注册上来了1W个观察者，每个执行1秒，那就需要一万秒，如果我们采用
        //同步通知，那么新的观察者就无法加进来，老的观察者就无法退出，这势必会影响整个使用，所以我们做了一定的系统，对于有外部调用且外部调用时长不可控的场景，
        //我们不采用同步方法，只是在内部对通过同步块的方式对观察者列表进行一份拷贝，然后再这个去调用更新操作。此方案带了一定的潜在风险（竞态条件）：
        //1. 可能导致已经退出的观察者收到信息；2. 新加入的观察者没有收到信息。
        observers.forEach(weathObserver -> {
            weathObserver.update();
        });
    }
}
