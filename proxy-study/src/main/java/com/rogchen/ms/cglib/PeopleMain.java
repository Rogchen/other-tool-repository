package com.rogchen.ms.cglib;

/**
 * @Description: cglib是针对类来实现代理的，
 * 原理是对指定的业务类生成一个子类，并覆盖其中业务方法实现代理。
 * 因为采用的是继承，所以不能对final修饰的类进行代理。
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/14 09:32
 **/
public class PeopleMain {

    public static void main(String[] args) {
        People p = new People();
        PeopleCglib pc = new PeopleCglib();
        People o = (People) pc.getInstance(p);
        o.sleep();
    }
}
