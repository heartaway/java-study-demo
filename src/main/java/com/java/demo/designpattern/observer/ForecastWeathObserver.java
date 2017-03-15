package com.java.demo.designpattern.observer;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-11 下午3:59
 */
public class ForecastWeathObserver implements WeathObserver {

    public ForecastWeathObserver(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("forcast weath observer ");
    }
}
