package com.rogchen.ms.jdk.dynamicProxy;

import com.rogchen.ms.jdk.Human;
import com.rogchen.ms.jdk.Man;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:56
 **/
public class ManMain {

    public static void main(String[] args) {
        //实现方式一
        Man m = new Man();
        ManProxy proxy = new ManProxy();
        Human result = (Human) proxy.instance(m);
        result.run();
        result.sleep();

        //实现方式二
        Man n = new Man();
        //因为只能代理接口的方法，所以 ManProxy1 handler = new ManProxy1(n);可用多态的形式接受
        InvocationHandler handler = new ManProxy1(n);
        Human h = (Human) Proxy.newProxyInstance(handler.getClass().getClassLoader(), n.getClass().getInterfaces(), handler);
        //也是可以的，因为没有使用类加器
//        Human h = (Human) Proxy.newProxyInstance(n.getClass().getClassLoader(),n.getClass().getInterfaces(),handler);
        h.run();
        h.sleep();
    }
}
