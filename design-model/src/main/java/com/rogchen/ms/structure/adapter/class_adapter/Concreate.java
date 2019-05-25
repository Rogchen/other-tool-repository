package com.rogchen.ms.structure.adapt.class_adapter;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/6 15:39
 **/
public class Concreate implements Targetable {
    @Override
    public void method() {
        System.out.println("this is stand class");
    }
}
