title: cglib动态代理

作用：不修改原有类的情况下插入我们需要的代码，常用于日志权限等

不足：类不能被final修饰

描述：采用的是生成一个子类继承被代理类方式，调用子类后再调用父类的方法。

**注意：cglib动态代理的被代理类不能被final修饰**

* 1、创建父类，实现父类的方法。
* 2、创建代理类实现MethodInterceptor接口，实现回调方法。
* 3、创建加强器，用来创建动态代理类(相当于jdk的绑定过程)，设置父类、回调方法。
* 4、实现

问题：

1、为什么cglib比jdk代理好用？

答：cglib生成的类是继承被代理类，被代理类无论有没有实现接口都可以使用，使用更加方便。

源码分析：
在创建加强器的时候使用Enhancer类是继承AbstractClassGenerator类，而cglib的核心就是AbstractClassGenerator类
在methodProxy实现回调的时候调用了AbstractClassGenerator来创建子类。

enhancer的创建
```enhancer
 private Object createHelper() {
        this.validate();
        if(this.superclass != null) {
            this.setNamePrefix(this.superclass.getName());
        } else if(this.interfaces != null) {
            this.setNamePrefix(this.interfaces[ReflectUtils.findPackageProtected(this.interfaces)].getName());
        }
        //调用父类的方法
        return super.create(KEY_FACTORY.newInstance(this.superclass != null?this.superclass.getName():null, ReflectUtils.getNames(this.interfaces), this.filter, this.callbackTypes, this.useFactory, this.interceptDuringConstruction, this.serialVersionUID));
    }
```
methodProxy调用AbstractClassGenerator
```methodProxy调用AbstractClassGenerator
private static class CreateInfo {
        Class c1;
        Class c2;
        NamingPolicy namingPolicy;
        GeneratorStrategy strategy;
        boolean attemptLoad;

        public CreateInfo(Class c1, Class c2) {
            this.c1 = c1;
            this.c2 = c2;
            AbstractClassGenerator fromEnhancer = AbstractClassGenerator.getCurrent();
            if(fromEnhancer != null) {
                this.namingPolicy = fromEnhancer.getNamingPolicy();
                this.strategy = fromEnhancer.getStrategy();
                this.attemptLoad = fromEnhancer.getAttemptLoad();
            }

        }
    }
```





