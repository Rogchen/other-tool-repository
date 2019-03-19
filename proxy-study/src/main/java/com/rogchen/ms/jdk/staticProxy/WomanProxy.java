package com.rogchen.ms.jdk.staticProxy;

import com.rogchen.ms.jdk.Woman;

/**
 * @Description: 人的代理类--代理man的实现类
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:18
 **/
public class WomanProxy {
    private Woman target;   ///组合一个业务实现类对象来进行真正的业务方法的调用

    public WomanProxy(Woman target) {
        this.target = target;
    }

    public void sleep() {
        System.out.println("我们准备开始睡觉了。。。。");
        target.sleep();
        System.out.println("睡醒要起来了。。。。");
    }

    public void run() {
        System.out.println("我们准备开始跑步了。。。。");
        target.run();
        System.out.println("我们已经跑不动了。。。。");
    }
}
