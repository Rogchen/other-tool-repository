package com.rogchen.ms.sampleFactory.duotai;

/**
 * @Description: 子类1
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:22
 **/
public class chidren1 extends parent {

    @Override
    public void say() {
        System.out.println("children1 say:hello children1!");
    }

    @Override
    protected void spell() {
        System.out.println("children1 spell:hello children1!");
    }
}
