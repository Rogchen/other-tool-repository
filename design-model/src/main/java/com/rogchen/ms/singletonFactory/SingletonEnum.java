package com.rogchen.ms.singletonFactory;

/**
 * @Description: 单例模式-枚举法[推荐用]
 * <p>
 * 借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，
 * 而且还能防止反序列化重新创建新的对象。
 * 可能是因为枚举在JDK1.5中才添加，所以在实际项目开发中，很少见人这么写过
 * 优点
 * 系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能。
 * 缺点
 * 当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new，可能会给其他开发人员造成困扰，特别是看不到源码的时候。
 * </p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 11:41
 **/
public enum SingletonEnum {
    instance;

    public void getInstance(){
    }

}
