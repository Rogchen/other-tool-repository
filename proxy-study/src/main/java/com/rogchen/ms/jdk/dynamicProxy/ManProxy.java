package com.rogchen.ms.jdk.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 实现InvocationHandler
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:43
 **/
public class ManProxy implements InvocationHandler {
    private Object target;//这其实业务实现类对象，用来调用具体的业务方法

    /**
     * 绑定业务对象并返回一个代理类
     */
    public Object instance(Object target) {
        this.target = target;  //接收业务实现类对象参数
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        Class cl = proxy.getClass();
        System.out.println(cl.getName());
        System.out.println("代理前。。。。。");
//        System.out.println("代理对象proxy" + proxy);
        System.out.println("代理方法method" + method);
        result = method.invoke(target, args);
        return result;
    }
}
