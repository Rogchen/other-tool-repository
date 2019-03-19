package com.rogchen.ms.jdk.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 17:31
 **/
public class ManProxy1 implements InvocationHandler {
    private Object target;

    public ManProxy1() {
    }

    public ManProxy1(Object target) {
        this.target = target;
    }
    private static final Class<?>[] constructorParams =
            { InvocationHandler.class };

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class cl = proxy.getClass();
        System.out.println(cl.getName());
        //如何将proxy的代理类$proxy0生成实现类???
        System.out.println("代理前。。。。。");
//        System.out.println("代理对象proxy" + proxy);
        System.out.println("代理方法method" + method);
        return method.invoke(target, args);
    }
}
