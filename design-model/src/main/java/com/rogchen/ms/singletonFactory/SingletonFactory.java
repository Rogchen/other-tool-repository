package com.rogchen.ms.singletonFactory;

/**
 * @Description: 单例模式-双重检查[推荐用] 重点是volatile
 * 优点：线程安全；延迟加载；效率较高
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 10:48
 **/
public class SingletonFactory {

    private static volatile SingletonFactory singletonFactory;

    private SingletonFactory(){}

    public static SingletonFactory getInstance() {
        if (singletonFactory == null) {
            synchronized (SingletonFactory.class) {
                //防止在进入同步锁的时候 刚好实例初始化完成。
                if (singletonFactory == null) {
                    singletonFactory = new SingletonFactory();
                }
            }
        }
        return singletonFactory;
    }

    public static void main(String[] args) {
        SingletonFactory factory = SingletonFactory.getInstance();
        System.out.println(factory);
        SingletonFactory factory1 = SingletonFactory.getInstance();
        System.out.println(factory1);
    }

}
