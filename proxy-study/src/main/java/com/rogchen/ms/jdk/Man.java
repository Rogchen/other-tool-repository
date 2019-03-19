package com.rogchen.ms.jdk;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/2/13 16:17
 **/
public class Man implements Human {

    @Override
    public void sleep() {
        System.out.println("我们正在睡觉。。。。");
    }

    @Override
    public void run() {
        System.out.println("我们正在奔跑。。。。");
    }
}
