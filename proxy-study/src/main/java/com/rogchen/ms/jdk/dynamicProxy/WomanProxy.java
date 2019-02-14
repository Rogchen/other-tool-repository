package com.rogchen.ms.jdk.dynamicProxy;

import com.rogchen.ms.jdk.Woman;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 没有实现接口无法实现
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 17:31
 **/
public class WomanProxy implements InvocationHandler {

    private Object target;

    public Object instance(Woman target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前。。。。");
        return method.invoke(target, args);
    }
}
