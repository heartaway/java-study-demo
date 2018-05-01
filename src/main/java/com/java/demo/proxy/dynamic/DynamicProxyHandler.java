package com.java.demo.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.java.demo.proxy.statics.Action;
import com.java.demo.proxy.statics.RealObject;

/**
 * Java 动态代理：基于接口的字节码代理类生成
 * 生成的代理类：继承 Proxy 类，实现指定接口；
 *
 * Cglib动态代理：基于子类的字节码代理类生成
 *
 * @author: heartaway
 * @create: 2018-04-09 上午11:40
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Action realObject;

    public DynamicProxyHandler(Action realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("delegate object do something");
        return method.invoke(realObject, args);
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealObject realObject = new RealObject();
        Action proxy = (Action)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {Action.class},
            new DynamicProxyHandler(realObject));
        proxy.doSomething();
    }
}
