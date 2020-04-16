package com.rogchen.ms.factoryMethodAbstroy;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 根据
 * @product: IntelliJ IDEA
 * @create by 20-3-16 14:30
 **/
public class ColorFactoryImpl extends AbstractFactory {

    @Override
    public Color getColor(String colorName) {
        if ("blue".equalsIgnoreCase(colorName)) {
            return new Blue();
        } else if ("red".equalsIgnoreCase(colorName)) {
            return new Red();
        }
        return null;
    }

    @Override
    public Shape getShape(String shapeName) {
        return null;
    }
}
