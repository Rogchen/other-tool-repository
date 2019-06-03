package com.rogchen.ms.structure.decorator;

/**
 * @Description: 这是我们真正要使用的，装饰器是红色圆
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/6/3 14:46
 **/
public class RedCircle extends ShapeDecorator {

    public RedCircle(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        //装饰器在这里使用-插入了我们装饰的东西
        printRed();
        shapeDecorator.draw();
    }

    private void printRed(){
        System.out.println("print red!");
    }

}
