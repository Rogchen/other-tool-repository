package com.rogchen.ms.singletonFactory;

/**
* @Description 单例模式-懒汉式[不可用] :线程不安全
 * <p>当一个线程刚好进入 if()另个线程也进入，就产生了多个实例</p>
* @Author Rogchen rogchen128@gmail.com
* @Created 2018/11/29 11:35
**/
public class SingletonLhsDmk {

    private static SingletonLhsDmk singleton;

    private SingletonLhsDmk() {}

    public static SingletonLhsDmk getInstance() {
        if (singleton == null) {
            synchronized (SingletonLhsDmk.class) {
                singleton = new SingletonLhsDmk();
            }
        }
        return singleton;
    }
}