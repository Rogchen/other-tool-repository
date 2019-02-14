title: 动态代理

作用：不修改原有类的情况下插入我们需要的代码，常用于日志权限等

不足：复杂、只能实现接口

**注意：jdk动态代理必须实现接口**
* 1、定义接口类Human
* 2、定义接口的实现类 man
* 3、定义代理类 manProxy
* 4、实现 manMain

问题？
1、为甚需要实现接口才能使用jdk动态代理

答：通过源码编译（后面实现原理有说明）可以知道动态代理是生成一个类继承Proxy类，因为java只能单继承所以只能通过实现来解决多继承问题

2、为什么生成的代理类一定要继承Proxy类,如果不继承的话我们也就不必要求被代理类实现接口了

答：我们是通过proxy生成一个类来进行代理，我们实现类可能继承其它类，因为为了通用jdk直接使用实现接口的方式来。（我的理解）
（ps:别人的解释：1.在需要继承proxy类获得有关方法和InvocationHandler构造方法传参的同时,java不能同时继承两个类，我们需要和想要代理的类建立联系，只能实现一个接口
2.需要反射获得代理类的有关参数，必须要通过某个类，反射获取有关方法，如本次测试用的 :printSomeThing
3.成功返回的是object类型，要获取原类，只能继承/实现，或者就是那个代理类
4.对具体实现的方法内部并不关心，这个交给InvocationHandler.invoke那个方法里去处理就好了，我只想根据你给我的接口反射出对我有用的东西。
5.考虑到设计模式，以及proxy编者编写代码的逻辑使然）

实现原理：
通过反编译可知最终生成一个类叫$Proxy0继承Proxy实现Human接口的类（java无法多继承）
`public final class $Proxy0 extends Proxy implements Human`
里面的方法就是我们实现类的方法，在每个方法内有个invoke的方法。
```
 public final void sleep()
      {
          try
          {
              super.h.invoke(this, m3, null);
              return;
          }
          catch (Error ) { }
          catch (Throwable throwable)
          {
              throw new UndeclaredThrowableException(throwable);
          }
      }
```
这就是调用我们代理类里面invoke的方法。所以说代理类必须实现InvocationHandler接口实现类。
底层实现：[参考1](https://www.cnblogs.com/9513-/p/8432276.html)
        [参考2](https://blog.csdn.net/zxysshgood/article/details/78684229)