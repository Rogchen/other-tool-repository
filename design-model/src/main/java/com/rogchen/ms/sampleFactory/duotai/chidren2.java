package com.rogchen.ms.sampleFactory.duotai;

/**
 * @Description: 子类2
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:22
 **/
public class chidren2 extends parent {

    @Override
    public void say() {
        System.out.println("children2 say:hello children1!");
    }

    @Override
    protected void spell() {
        System.out.println("children2 spell:hello children1!");
    }
}
