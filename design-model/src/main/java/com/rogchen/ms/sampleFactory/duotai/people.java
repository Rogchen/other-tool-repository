package com.rogchen.ms.sampleFactory.duotai;

/**
 * @Description: 多态是子类继承父类，根据不同的实例化对象来进行调用。
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/7 10:24
 **/
public class people {

    public static void main(String[] args) {
        parent p = new chidren1();
        p.say();
        p.spell();
        parent pp = new chidren2();
        pp.say();
        pp.spell();
    }
}
