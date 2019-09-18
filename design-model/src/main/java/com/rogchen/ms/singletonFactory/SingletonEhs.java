package com.rogchen.ms.singletonFactory;

/**
 * @Description: 单例模式-饿汉式(静态常量发)[可用]
 * <p>
 * 优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。
 * 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费。
 * </p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 11:11
 **/
public class SingletonEhs {

    private final static SingletonEhs INSTANCE = new SingletonEhs();

    public static SingletonEhs getInstance() {
        return INSTANCE;
    }


    public static void main(String[] args) {
        SingletonEhs els = SingletonEhs.getInstance();
        System.out.println(els);
        SingletonEhs els1 = SingletonEhs.getInstance();
        System.out.println(els1);
    }
}
