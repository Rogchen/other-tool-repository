package com.rogchen.ms.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/14 09:12
 **/
public class PeopleCglib implements MethodInterceptor {

    private Object target;

    /**
     *相当于JDK动态代理中的绑定
     * Enhancer是继承AbstractClassGenerator，
     * AbstractClassGenerator是在menthodProxy里面调用的抽象生成类方法
     */
    public Object getInstance(Object target){
        this.target = target;
        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.target.getClass());
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    // 实现回调方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        System.out.println("代理前处理。。。。");
        Object ob = proxy.invokeSuper(o,objects);
        System.out.println(ob);
        return null;
    }
}
