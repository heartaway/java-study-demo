package com.java.demo.classcreate;

/**
 * <p>静态工厂方法代替默认共有方法</p>
 * User: 心远
 * Date: 14/12/14
 * Time: 下午5:09
 */
public class StaticConstructorMethod {


    public StaticConstructorMethod() {
    }

    public static StaticConstructorMethod newInstance() {
        return new StaticConstructorMethod();
    }
}
