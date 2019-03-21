package com.rogchen.ms.factoryMethod;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/3/20 15:54
 **/
public class PeoPleMain {

    public static void main(String[] args) {
        RunFactoryInterface man = new ManRunFactoryImpl();
        man.product().run();
    }
}
