package com.rogchen.ms.jdk.staticProxy;

import com.rogchen.ms.jdk.Woman;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:23
 **/
public class WomanMainTest {

    public static void main(String[] args) {
        Woman m = new Woman();
        WomanProxy p = new WomanProxy(m);
        p.sleep();
        p.run();
    }
}
