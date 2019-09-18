package com.rogchen.ms.singletonFactory;

/**
 * @Description: 单例模式-饿汉式(静态代码块)[可用]
 * <p>
 * 优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
 * 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费。
 * </p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 11:11
 **/
public class SingletonEhsStatic {

    private static SingletonEhsStatic instance;

    static {
        instance = new SingletonEhsStatic();
    }


    private SingletonEhsStatic() {
    }

    public static SingletonEhsStatic getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        SingletonEhsStatic els = SingletonEhsStatic.getInstance();
        System.out.println(els);
        SingletonEhsStatic els1 = SingletonEhsStatic.getInstance();
        System.out.println(els1);
    }
}
