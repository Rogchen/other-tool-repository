package com.rogchen.ms.structure.decorator;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/6/3 14:38
 **/
public class TestShape {

    /**
     * @Description
     * 1、定义一个普通类实现了通用接口
     * 2、定义一个抽象类并实现通用接口来作为装饰器基类
     * 3、定义一个装饰类继承装饰器基类，通过子父类之间关系来实现，并插入我们需要装饰的。
     *
     * @Author Rogchen
     * @Date 14:56 2019/6/3
     * @Param [args]
     * @Return void
    **/
    public static void main(String[] args) {
        System.out.println("普通方式的调用：");
        Shape circle = new Circle();
        circle.draw();

        System.out.println("使用装饰器输出:");
        Shape redCircle = new RedCircle(circle);
        redCircle.draw();

    }
}
