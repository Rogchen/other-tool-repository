package com.rogchen.ms.structure.decorator;

/**
 * @Description: 定义一个抽象的装饰类 实现通用接口
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/6/3 14:41
 **/
public abstract class ShapeDecorator implements Shape {
    protected Shape shapeDecorator;

    public ShapeDecorator(Shape shape) {
        this.shapeDecorator = shape;
    }

    @Override
    public void draw() {
        shapeDecorator.draw();
    }
}
