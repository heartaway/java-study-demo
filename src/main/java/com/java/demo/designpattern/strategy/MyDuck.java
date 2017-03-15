package com.java.demo.designpattern.strategy;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-11 上午11:15
 */
public class MyDuck extends Duck {

    public MyDuck() {
    }

    @Override
    public void display() {
        System.out.println("MyDuck is display");
    }

    public static void main(String[] args) {
        Duck myDuck = new MyDuck();
        myDuck.display();
        myDuck.setFlyBehavior(new FlyWithWing());
        myDuck.performFly();
        myDuck.setFlyBehavior(new FlyNothing());
        myDuck.performFly();
    }
}
