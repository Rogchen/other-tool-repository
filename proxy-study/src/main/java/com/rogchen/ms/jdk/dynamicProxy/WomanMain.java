package com.rogchen.ms.jdk.dynamicProxy;

import com.rogchen.ms.jdk.Woman;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:56
 **/
public class WomanMain {

    public static void main(String[] args) {
        //实现方式一
        Woman m = new Woman();
        WomanProxy proxy = new WomanProxy();
        Woman result = (Woman) proxy.instance(m);
        result.run();
        result.sleep();
    }
}
