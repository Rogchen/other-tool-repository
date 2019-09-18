package com.rogchen.ms.structure.decorator;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/6/3 14:37
 **/
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("shape is circle");
    }
}
