package com.rogchen.ms.singletonFactory;

/**
 * @Description: 单例模式-懒汉式[不可用]-线程不安全：初始化多线程调用时候产生多个实例
 * <p>
 * 这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。如果在多线程下，
 * 一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，
 * 另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式。
 * </p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 11:22
 **/
public class SingletonLhs {

    private static SingletonLhs instance;

    private SingletonLhs (){}

    public static SingletonLhs getInstance() {
        if (instance == null) {
            instance = new SingletonLhs();
        }
        return instance;
    }
}
