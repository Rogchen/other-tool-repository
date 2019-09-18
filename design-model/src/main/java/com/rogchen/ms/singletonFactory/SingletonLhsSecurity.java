package com.rogchen.ms.singletonFactory;

/**
 * @Description: 单例模式-懒汉式[不可用]-线程安全
 * <p>
 * 做个线程同步就可以了，于是就对getInstance()方法进行了线程同步。
 * 缺点：效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。
 * 而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接return就行了。
 * 方法进行同步效率太低要改进。
 * </p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 11:22
 **/
public class SingletonLhsSecurity {

//    如果下面这种写法就是线程安全-推荐使用
//    private static volatile SingletonLhsSecurity singletonLhsSecurity;
    private static SingletonLhsSecurity instance;

    private SingletonLhsSecurity(){}

    public static synchronized SingletonLhsSecurity getInstance() {
        if (instance == null) {
            instance = new SingletonLhsSecurity();
        }
        return instance;
    }
}
