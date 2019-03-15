title: 单例模式

![单例图](https://images2017.cnblogs.com/blog/401339/201709/401339-20170929202530606-709085396.png)
## 2、简单工厂模式(可用看成工厂模式种特殊一种)【代码块：sampleFactory】
    建立一个工厂类，对实现了同一接口的一些类进行实例的创建。简单工厂模式的实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类（这些产品类继承自一个父类或接口）的实例。


**_推荐_** 使用双重检查（singletonFactory）、静态内部类（singletonStatic）

双重检查：
```$singletonFactory
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
```
volatile 具有可见性、有序性，不具备原子性；
使用场景：适用于对变量的写操作不依赖于当前值，对变量的读取操作不依赖于非volatile变量。
     适用于读多写少的场景。
     可用作状态标志。通俗将就是事实更新到内存中去。

静态内部类
 ```$SingletonStatic
public class SingletonStatic {

    private SingletonStatic() {}

    private static class SingletonStaticInstance {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        return SingletonStaticInstance.INSTANCE;
    }
}
```