package com.rogchen.ms.jdk.staticProxy;

import com.rogchen.ms.jdk.Man;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:23
 **/
public class MainTest {

    public static void main(String[] args) {
        Man m = new Man();
        PersonProxy p = new PersonProxy(m);
        p.sleep();
        p.run();
    }
}
