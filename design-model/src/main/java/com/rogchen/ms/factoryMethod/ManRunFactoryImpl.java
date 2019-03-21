package com.rogchen.ms.factoryMethod;

/**
 * @Description: 男人奔跑工厂实现类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/20 15:53
 **/
public class ManRunFactoryImpl implements RunFactoryInterface{

    @Override
    public Human product() {
        return new Man();
    }
}
