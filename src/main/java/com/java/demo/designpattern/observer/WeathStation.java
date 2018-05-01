package com.java.demo.designpattern.observer;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-11 下午3:58
 */
public class WeathStation {

    public static void main(String[] args) {
        WeathData weathData = new WeathData();
        WeathObserver observer = new CurrentTimeWeathObserver(weathData);
        WeathObserver forcastObserver = new ForecastWeathObserver(weathData);
        weathData.notifyObservers();
    }
}
