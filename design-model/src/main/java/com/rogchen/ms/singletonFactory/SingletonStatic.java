package com.rogchen.ms.singletonFactory;

/**
* @Description 单例模式-静态内部类[推荐]
* @Author Rogchen rogchen128@gmail.com
* @Created 2018/11/29 10:55
**/
public class SingletonStatic {

    private SingletonStatic() {}

    private static class SingletonStaticInstance {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        return SingletonStaticInstance.INSTANCE;
    }
}