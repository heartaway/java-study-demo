package com.java.demo.serializable.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * <p>
 * 本类并发实际模拟hessian调用，而是熟悉HessianProxyFactory的工作原理
 * 两个重要的类：HessianProxyFactory  和  HessianServlet
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/3/18
 * Time: 下午7:43
 */
public class HelloHessian  implements IHelloHessian{

    private static final long serialVersionUID = 9000370610610488251L;

    public String sayHello() {
        return "hello";
    }

    public static void main(String[] args) throws Exception{
        Class<?> myClass = Class.forName("com.java.demo.serializable.hessian.IHelloHessian");

        String url = "http://localhost:8082/J2EE_sjsky/HessianService";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            IHelloHessian hello = (IHelloHessian)factory.create(
                    IHelloHessian.class, url);
            System.out.printf(hello.sayHello());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
